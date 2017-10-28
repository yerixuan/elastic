package com.elastic.elastic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import netscape.javascript.JSObject;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * Created by Administrator on 2017/10/25.
 */
public class ElasticsearchTest {

   static  Logger logger = LoggerFactory.getLogger(ElasticsearchTest.class);

    public static void main(String[] args)  throws Exception{

//        Settings settings = Settings.builder()
//               // .put("cluster.name", "test-elk")
//            //    .put("http.basic.user","admin")
//              //  .put("http.basic.password", "admin")
//               // .put("Authorization","Basic YWRtaW46YWRtaW4=")
//                .build();
//        TransportClient client = new PreBuiltTransportClient(settings)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//       insert(client);

//        RestClientBuilder builder = RestClient.builder(new HttpHost("10.201.3.102", 9300, "https"));
//        Header[] defaultHeaders = new Header[]{new BasicHeader("Authorization", "Basic YWRtaW46YWRtaW4=")};
//        builder.setDefaultHeaders(defaultHeaders);
//
//        Response response = builder.build().performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
//        System.out.println(EntityUtils.toString(response.getEntity()));

//        System.setProperty("https.protocols", "TLSv1.2,SSLv3");
//        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
//            @Override
//            public boolean verify(String paramString, SSLSession paramSSLSession) {
//                return true;
//            }
//        });
//        trustAllHttpsCertificates();
//
        //本地先yongokhttp代替
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//        OkHttpClient httpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://127.0.0.1:9200/_search?q=*&size=1") //  http://10.201.3.33:9200
//                .addHeader("accept","*/*")
//         //       .addHeader("Authorization", "Basic dGVzdDp0ZXN0") // Basic dGVzdDp0ZXN0  Basic YWRtaW46YWRtaW4=
//                .addHeader("connection","Keep-Alive")
//                .addHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
//                .build();
//        Call call = httpClient.newCall(request);




     //   okhttp3.Response response = call.execute();
     //   System.out.println(response.body().string());


//        String param = "{'query' : {'match_all' : {}} , 'from' : 0, 'size' : 2  }";
//
//        sendPost("https://10.201.3.102:9200/_search", param);


        Map<String,Object> queryMap = new HashMap<String, Object>();
        Map<String,Object> paramMap1 = new HashMap<String,Object>();
        Map<String,Object> paramMap2 = new HashMap<String,Object>();
        Map<String,Object> paramMap3 = new HashMap<String,Object>();
        Map<String,Object> paramMap4 = new HashMap<String,Object>();

        Map<String,Object> sortMap1 = new HashMap<String,Object>();
        Map<String,Object> sortMap2 = new HashMap<String,Object>();

        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        /**
         * //es请求参数格式
         {
         //排序条件
         "sort": {
         "@timestamp": {
         "order": "desc",
         "unmapped_type": "boolean"
         }
         },
         //查询条件
         "query": {
         "bool": {
         "must": {
         "query_string": {
         "query": "context.userName:\"u_nl0xx@kd.ssj\" &&level:\"INFO\" &&_index:\"ls-ssj-money-*\"",
         "analyze_wildcard": true
         }
         }
         }
         },
         //分页条件
         "from": 1,
         "size": 5
         }
         */
        queryMap.put("from",0);
        queryMap.put("size",10);
        paramMap4.put("analyze_wildcard",true);
        paramMap4.put("query","context.userName:"+"\""+10+"\""+" && ("+"qu"+")");
        paramMap3.put("query_string",paramMap4);
        paramMap2.put("must",paramMap3);
        paramMap1.put("bool",paramMap2);
        queryMap.put("query",paramMap1);

        sortMap2.put("order","desc");
        sortMap2.put("unmapped_type","boolean");
        sortMap1.put("@timestamp",sortMap2);
        list.add(sortMap1);
        queryMap.put("sort",sortMap1);

    //    sendPost("https://10.201.3.102:9200/_search", JSONObject.toJSONString(queryMap));
   //     System.out.println(JSONObject.toJSONString(queryMap));


        OkHttpClient httpClient = new OkHttpClient();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        RequestBody body =  RequestBody.create(JSON, queryGruop("","",""));
        Request request = new Request.Builder()
                .url("http://127.0.0.1:9200/_search") //  http://10.201.3.33:9200
                 .post(body)
                .addHeader("accept","*/*")
                //       .addHeader("Authorization", "Basic dGVzdDp0ZXN0") // Basic dGVzdDp0ZXN0  Basic YWRtaW46YWRtaW4=
                .addHeader("connection","Keep-Alive")
                .addHeader("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                .build();

      //  Call call = httpClient.newCall(request);

       // System.out.println(call.execute().body().string());
    //    System.out.println(parseGroup(call.execute().body().string()));
     //   System.out.println(sendPost("http://127.0.0.1:9200/myindex/_search",queryGruop()));

        Map map =new HashMap();
        map.put("query",getQueryString("groupname","","testname"));
        System.out.println(JSONObject.toJSONString(map));

    }


    public static String setField() {

        Map p1 = new HashMap();
        Map p2 = new HashMap();
        Map p3 = new HashMap();

        p3.put("type","text");
        p3.put("fielddata",true);
        p2.put("group",p3);
        p1.put("properties",p2);
        return JSONObject.toJSONString(p1);
    }

    public static String parseGroup(String json) {

        JSONObject obj = JSONObject.parseObject(json);

        JSONArray result =  obj.getJSONObject("aggregations").getJSONObject("group").getJSONArray("buckets");

        List list = new ArrayList();
        for(int i= 0 ;i < result.size() ;i++ ) {
           list.add(((JSONObject)result.get(i)).get("key"));
        }

         return JSONObject.toJSONString(list);
    }

    private static Map getQueryString(String group, String endpoint, String name) {
        Map queryMap = new HashMap();

        if(group.isEmpty()) {
            queryMap.put("match_all","{}");
            return queryMap;
        }

        //以下部分 group不为空
        Map boolParam = new HashMap();
        Map shouldParam = new HashMap();
        Map mathchParam = new HashMap();
        Map groupParam = new HashMap();
        List<Map> mathchList = new ArrayList<>();

        groupParam.put("group",group);
        mathchParam.put("match",groupParam);
        mathchList.add(mathchParam);

      if(!endpoint.isEmpty() && !name.isEmpty()) {
          Map endpointMatch = new HashMap();
          Map nameMatch = new HashMap();
          Map endpointParam = new HashMap();
          Map nameParam = new HashMap();
          endpointParam.put("endpoint",endpoint);
          nameParam.put("name",name);
          endpointMatch.put("match",endpointParam);
          nameMatch.put("match", nameParam);
          mathchList.add(endpointMatch);
          mathchList.add(nameMatch);
      }
      if(!endpoint.isEmpty() && name.isEmpty()) {
          Map endpointMatch = new HashMap();
          Map endpointParam = new HashMap();
          endpointParam.put("endpoint",endpoint);
          endpointMatch.put("match",endpointParam);
          mathchList.add(endpointMatch);
      }
      if(endpoint.isEmpty() && !name.isEmpty()) {
          Map nameMatch = new HashMap();
          Map nameParam = new HashMap();
          nameParam.put("name",name);
          nameMatch.put("match", nameParam);
          mathchList.add(nameMatch);
      }

        shouldParam.put("must",mathchList);
        boolParam.put("bool",shouldParam);
      return  boolParam;
    }


    public static String queryGruop(String group ,String endpoint ,String name) throws  Exception{

       Map<String, Object>  param1 = new HashMap();
       Map<String, Object>  param2 = new HashMap();
       Map<String, Object>  param3 = new HashMap();
       Map<String, Object> param4 = new HashMap();
       Map<String, Object> query = new HashMap();

       if(group.isEmpty() && endpoint.isEmpty() && name.isEmpty() ) {
           query.put("match_all","{}");
       }
       if(!group.isEmpty()) {
           Map map = new HashMap();
           query.put("match_all", map.put("group",group));
       }
       if(!endpoint.isEmpty()) {
           Map map = new HashMap();
           query.put("match_all", map.put("endpoint",endpoint));
       }
        if(!name.isEmpty()) {
            Map map = new HashMap();
            query.put("match_all", map.put("name",name));
        }



       param4.put("field", "group.keyword");
       param3.put("terms", param4);
       param2.put("group",param3);
       param1.put("aggs", param2);
       param1.put("query","");
    //   param5.put("match_all","{}");
    //    param1.put("query",param5);
    //   param1.put("from", 1);
       param1.put("size", 0);


        System.out.println(JSONObject.toJSONString(param1));

            return JSONObject.toJSONString(param1);
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            System.setProperty("https.protocols", "TLSv1.2,SSLv3");
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier()
            {
                @Override
                public boolean verify(String paramString, SSLSession paramSSLSession) {
                    return true;
                }
            });
            trustAllHttpsCertificates();
       //     String auth = new String("YWRtaW46YWRtaW4=");
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
       //     conn.setRequestProperty("Authorization","Basic YWRtaW46YWRtaW4=");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
          //  LOG.error("发送 POST 请求出现异常！", e);
            System.out.println("发送 POST 请求出现异常！");
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
             //   LOG.error("关闭资源异常", ex);
            }
        }
        return result;
    }





    private static SSLContext trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new MiTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        return sc;
    }

    static class MiTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    private static SSLContext allowAllSSL() {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


       return sc;

    }





    public static void insertTestData(TransportClient client) {

    }


    public static void groupTest(TransportClient client){

        //构建查询请求体
        SearchRequestBuilder search = client.prepareSearch("test4");//.setTypes("gv_test");

        //分组字段是id，排序由多个字段排序组成
        TermsAggregationBuilder tb= AggregationBuilders.terms("group").field("group").order(Terms.Order.compound(
                Terms.Order.aggregation("sum_count",false)//先按count，降序排
                ,
                Terms.Order.aggregation("sum_code",true)//如果count相等情况下，使用code的和排序
        ));

        //求和字段1
        SumAggregationBuilder sb= AggregationBuilders.sum("sum_count").field("count");
        //求和字段2
        SumAggregationBuilder sb_code= AggregationBuilders.sum("sum_code").field("code");

        tb.subAggregation(sb);//添加到分组聚合请求中
        tb.subAggregation(sb_code);//添加到分组聚合请求中

        //将分组聚合请求插入到主请求体重
        search.addAggregation(tb);
        //发送查询，获取聚合结果
        Terms tms=  search.get().getAggregations().get("group");
        //遍历每一个分组的key
        for(Terms.Bucket tbb:tms.getBuckets()){
            //获取count的和
            Sum sum= tbb.getAggregations().get("sum_count");
            //获取code的和
            Sum sum2=tbb.getAggregations().get("sum_code");
            System.out.println(tbb.getKey()+"  " + tbb.getDocCount() +"  "+sum.getValue()+"  "+sum2.getValue());
        }
        //释放资源
        client.close();
    }

    private static void group(TransportClient client) {

        SearchRequestBuilder sbuilder = client.prepareSearch();
        TermsAggregationBuilder teamAgg= AggregationBuilders.terms("player_count ").field("id");
        sbuilder.addAggregation(teamAgg);
        SearchResponse response = sbuilder.execute().actionGet();
        for(SearchHit hit : response.getHits()) {
            System.out.println();
            System.out.println(hit.getIndex()+ "  "+hit.getType()+ "  "+ hit.getId() + hit.getSource());
        }
    }



    private static void queryByField(TransportClient client) {
        SearchRequestBuilder responsebuilder = client.prepareSearch("test");//.setTypes("type");
        SearchResponse  response  =  client.prepareSearch("test")
              //  .setTypes("type",  "type3", "type5")
            //    .addSort("date", SortOrder.DESC)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
              //  .setQuery(QueryBuilders.queryStringQuery("trying out Elasticsearch"))
                 .setQuery(QueryBuilders.matchPhraseQuery("group","server-side"))
                .setQuery(QueryBuilders.matchPhrasePrefixQuery("endpoint",""))//  Query
                // .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))          //  Filter
                .setFrom(0).setSize(100).setExplain(true)
                .execute()
                .actionGet();

        for(SearchHit hit : response.getHits()) {
            System.out.println();
            System.out.println(hit.getIndex()+ "  "+hit.getType()+ "  "+ hit.getId() + hit.getSource());
        }
    }

    private static void queryAll(TransportClient client) {

        SearchRequestBuilder responsebuilder = client.prepareSearch("test4");//.setTypes("type");


        //查询所有
        SearchResponse myresponse = responsebuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
               // .addSort("date", SortOrder.DESC)
                .setFrom(0).setSize(100).setExplain(true).execute().actionGet();


        for(SearchHit hit : myresponse.getHits()) {
            System.out.println();
            System.out.println(hit.getIndex()+ "  "+hit.getType()+ "  "+ hit.getId() + hit.getSource());
        }
    }

    private  static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private static String getID() {
        return UUID.randomUUID().toString();

    }

    public static void createDate() throws Exception{

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        for(int i= 0; i<100 ; i++) {
            client.prepareIndex("index", i + "", getID())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("group","server-side")
                            .field("endpoint", "172.22.25.48/uc")
                            .field("name", "response-time")
                            .field("data" + i, getDate())
                            .field("user" + i, "user" + i)
                            .field("message" + "i", "message" + i)
                            .endObject()
                    )
                    .get();


            client.prepareIndex("index", i + "", getID())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("group","web-side")
                            .field("endpoint", "192.168.1.1/bc")
                            .field("name", "bc-response-time")
                            .field("data" + i, getDate())
                            .field("user" + i, "user" + i)
                            .field("message" + "i", "message" + i)
                            .endObject()
                    )
                    .get();



            client.prepareIndex("index", i + "", getID())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("group","client-side")
                            .field("endpoint", "127.0.0.1/common")
                            .field("name", "common-response-time")
                            .field("data" + i, getDate())
                            .field("user" + i, "user" + i)
                            .field("message" + "i", "message" + i)
                            .endObject()
                    )
                    .get();


        }

    }

    //聚合数据
    public static void aggregations(TransportClient client) {

        SearchResponse sr = client.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("agg1").field("user2")
                )
//                .addAggregation(
//                        AggregationBuilders.dateHistogram("agg2")
//                                .field("birth")
//                                .dateHistogramInterval(DateHistogramInterval.YEAR)
//                )
                .get();




    }

    public static void insert(TransportClient client) throws  Exception{
        //插入数据
        for(int i = 0 ; i < 1000 ; i++) {
        IndexResponse response1 = client.prepareIndex("myindex", "mytype", getID())
                .setSource(jsonBuilder()
                        .startObject()
                        .field("group", "server-side")
                        .field("endpoint", "172.22.25.48/uc")
                        .field("name", "uc_response_time")
                        .field("value","uc"+i)
                        .field("date", getDate())
                        .endObject()
                )
                .get();

            client.prepareIndex("myindex", "mytype", getID())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("group", "client-side")
                            .field("endpoint", "172.22.25.48/book")
                            .field("name", "book_response_time")
                            .field("value","book"+i)
                            .field("date", getDate())
                            .endObject()
                    )
                    .get();

            client.prepareIndex("myindex", "mytype", getID())
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("group", "server-side")
                            .field("endpoint", "172.22.25.48/commom")
                            .field("name", "commom_response_time")
                            .field("value","commom"+i)
                            .field("date", getDate())
                            .endObject()
                    )
                    .get();

    }
    }
//
//
//    public static void insertTest(TransportClient client) throws  Exception {
//
//
//
//             client.prepareIndex("test4", "test_type3", getID())
//                    .setSource(jsonBuilder()
//                            .startObject()
//                            .field("id", 1)
//                            .field("count", 3)
//                            .field("code", 1)
//                            .field("date", getDate())
//                            .field("group","server-side")
//                            .field("endpoint", "usercenter")
//                            .field("name","ssj-uc")
//                            .endObject()
//                    )
//                    .get();
//
//        client.prepareIndex("test4", "test_type3", getID())
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("id", 2)
//                        .field("count", 4)
//                        .field("code", 1)
//                        .field("date", getDate())
//                        .field("group","server-side")
//                        .field("endpoint", "bookcenter")
//                        .field("name","ssj-book")
//                        .endObject()
//                )
//                .get();
//
//        client.prepareIndex("test4", "test_type3", getID())
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("id", 1)
//                        .field("count", 5)
//                        .field("code", 2)
//                        .field("date", getDate())
//                        .field("group","client-side")
//                        .field("endpoint", "common")
//                        .field("name","ssj-common")
//                        .endObject()
//                )
//                .get();
//
//
//        client.prepareIndex("test4", "test_type3", getID())
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("id", 2)
//                        .field("count", 7)
//                        .field("code", 1)
//                        .field("date", getDate())
//                        .field("group","client-side")
//                        .field("endpoint", "common")
//                        .field("name","ssj-common2")
//                        .endObject()
//                )
//                .get();
//
//        client.prepareIndex("test4", "test_type3", getID())
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("id", 3)
//                        .field("count", 11)
//                        .field("code", 7)
//                        .field("date", getDate())
//                        .field("group","other-side")
//                        .field("endpoint", "other")
//                        .field("name","ssj-other")
//                        .endObject()
//                )
//                .get();
//        }


}
