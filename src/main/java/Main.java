import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;


import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static Gson g = new Gson();

    public static <T> T post(String url, Type typeOfSrc) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(instream));
                String inputLine;
                StringBuilder result = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }
                in.close();
               // System.out.println(result);
                JsonParser parser;

                return g.fromJson(String.valueOf(result),typeOfSrc);
            }
        }
        throw new Exception("Shit");
    }
    public static void main(String[] args) throws Exception {
        String loginUrl = "https://inkbunny.net/api_login.php?output_mode=json&username=guest";
        SIDObject result = post(loginUrl,SIDObject.class);

            System.out.println(result);
            String ratingsUrl =
                    "https://inkbunny.net/api_userrating.php?output_mode=json&sid=" +
                    (String) result.getSid()
                    +
                    "&tag[2]=yes&tag[3]=yes&tag[4]yes&tag[5]yes";
            result = post(ratingsUrl,SIDObject.class);

                String sid = result.getSid();
                String searchUrl =
                        "https://inkbunny.net/api_search.php?output_mode=json&sid="
                        + sid +
                        "&get_rid=yes&type=12";
                SearchObject searchObject = post(searchUrl,SearchObject.class);

                    String rid = searchObject.getRid();
                    sid = searchObject.getSid();
                    int pageCount = searchObject.getPages_count();
                    List<Thread> threads = new ArrayList<>();
                    for(int page = 1; page<=pageCount;page++){
                        if(threads.size() < 6){
                            String finalSid = sid;
                            int finalPage = page;
                            Thread t = new Thread(() -> processPage(finalSid, rid, finalPage));
                            threads.add(t);
                            t.start();
                        }
                        else{
                          for(Thread thread: threads){
                              thread.join();
                          }
                            threads = new ArrayList<>();
                            String finalSid = sid;
                            int finalPage = page;
                            Thread t = new Thread(() -> processPage(finalSid, rid, finalPage));
                            threads.add(t);
                            t.start();
                        }


                    }




    }

    public static void processPage(String sid, String rid, int page) {
        String pageUrl =
                "https://inkbunny.net/api_search.php?output_mode=json&sid="
                        + sid +
                        "&rid=" + rid+
                        "&page=" + page;
        try{
            SearchObject result = post(pageUrl,SearchObject.class);

            SubmissionObject[] results = result.getSubmissions();
            for(int x = 0; x<results.length; x++){
                SubmissionObject json = results[x];
                String fileName = " (" + json.getSubmission_id() + ") " + json.getFile_name() ;
               // fileName = fileName.replaceAll(" /[^'?\\w.]|_/g", "").replaceAll(" ", "").replaceAll(":", "").replaceAll("\\\\", "-").replaceAll("[\\\\[\\\\](){}]","");
               // fileName = fileName.replaceAll(" ", "");
                getFile(json.getFile_url_full(), fileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getFile(String url, String fileName) throws IOException {
        URL website = new URL(url);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

}
