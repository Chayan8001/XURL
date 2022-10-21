package com.crio.shorturl;
import java.util.*;

class XUrlImpl implements XUrl
{
    private HashMap<String , String> url;
    private HashMap<String , Integer> hitCount;
    private int count;
    public XUrlImpl()
    {
        this.url = new HashMap<>();
        this.hitCount = new HashMap<>();
    }
    private static String getAlphaNumericString(int n)
    {
    
     // chose a Character random from this String
     String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    
     // create StringBuffer size of AlphaNumericString
     StringBuilder sb = new StringBuilder(n);
    
     for (int i = 0; i < n; i++) {
    
      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index
       = (int)(AlphaNumericString.length()
         * Math.random());
    
      // add Character one by one in end of sb
      sb.append(AlphaNumericString
         .charAt(index));
     }
    
     return sb.toString();
    }
  public  String registerNewUrl(String longUrl)
  {
    String shortUrl ="http://short.url/";
    if( !url.isEmpty() && url.containsKey(longUrl))
    {
        shortUrl = url.get(longUrl);
    }
    else
    {
      boolean flag = true;
      while(flag == true)
      {
        shortUrl =shortUrl +  getAlphaNumericString(9);
        if(url.containsValue(shortUrl))
        {
          flag = true;
        }
        else
        {
          flag = false;
        }
      }
      registerNewUrl(longUrl , shortUrl);
    }
    return shortUrl;
  }
  public String registerNewUrl(String longUrl, String shortUrl)
  {
    if(url.containsValue(shortUrl))
    {
      return null;
    }
    else
    {
      url.put(longUrl , shortUrl);
    }
    return shortUrl;
  }
  public String getUrl(String shortUrl)
  {
    this.count =  this.count+1;
    String longUrl = "";
    if(url.containsValue(shortUrl))
    {

      longUrl =  getLongUrl(shortUrl);
      // ---------------- adding hitcount for longUrl
      if(!hitCount.isEmpty() && hitCount.containsKey(longUrl))
        hitCount.put(longUrl, hitCount.get(longUrl) + 1);
      else
        hitCount.putIfAbsent(longUrl, 1);
      //-----------
      return longUrl;
    }
    else
      return null;
  }
  public String getLongUrl(String shortUrl)
  {
    for (Map.Entry<String,String> url : url.entrySet())
    {
      if(url.getValue() == shortUrl)
      {
        return url.getKey();
      }
    }
    return null;
  }

  // Return the number of times the longUrl has been looked up using getUrl()
  public Integer getHitCount(String longUrl)
  {
    int n=0;
    if(!hitCount.isEmpty() && hitCount.containsKey(longUrl))
    {
        n = hitCount.get(longUrl);
    }
    return n;
  }

  // Delete the mapping between this longUrl and its corresponding shortUrl
  // Do not zero the Hit Count for this longUrl
  public String delete(String longUrl)
  {
    url.remove(longUrl);
    return null;
  }

}