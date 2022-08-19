package questions._11_graph.search.string;

/*

Given a url startUrl and an interface HtmlParser, implement a web crawler to crawl all links that are under the same hostname as startUrl.

Return all urls obtained by your web crawler in any order.

Your crawler should:
    Start from the page: startUrl
    Call HtmlParser.getUrls(url) to get all urls from a webpage of given url.
    Do not crawl the same link twice.
    Explore only the links that are under the same hostname as startUrl.

As shown in the example url above, the hostname is example.org. For simplicity sake, you may assume all urls use http protocol without any port specified.
For example, the urls http://leetcode.com/problems and http://leetcode.com/contest are under the same hostname, while urls http://example.org/test and http://example.com/abc are not under the same hostname.

The HtmlParser interface is defined as such:

interface HtmlParser {
  // Return a list of all urls from a webpage of given url.
  public List<String> getUrls(String url);
}

Below are two examples explaining the functionality of the problem, for custom testing purposes you'll have three variables urls, edges and startUrl.
Notice that you will only have access to startUrl in your code, while urls and edges are not directly accessible to you in code.


Example 1:
    Input:
    urls = [
      "http://news.yahoo.com",
      "http://news.yahoo.com/news",
      "http://news.yahoo.com/news/topics/",
      "http://news.google.com",
      "http://news.yahoo.com/us"
    ]
    edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
    startUrl = "http://news.yahoo.com/news/topics/"
    Output: [
      "http://news.yahoo.com",
      "http://news.yahoo.com/news",
      "http://news.yahoo.com/news/topics/",
      "http://news.yahoo.com/us"
    ]

Example 2:
    Input:
    urls = [
      "http://news.yahoo.com",
      "http://news.yahoo.com/news",
      "http://news.yahoo.com/news/topics/",
      "http://news.google.com"
    ]
    edges = [[0,2],[2,1],[3,2],[3,1],[3,0]]
    startUrl = "http://news.google.com"
    Output: ["http://news.google.com"]
    Explanation: The startUrl links to all other pages that do not share the same hostname.


Constraints:
    1 <= urls.length <= 1000
    1 <= urls[i].length <= 300
    startUrl is one of the urls.
    Hostname label must be from 1 to 63 characters long, including the dots, may contain only the ASCII letters from 'a' to 'z', digits  from '0' to '9' and the hyphen-minus character ('-').
    The hostname may not start or end with the hyphen-minus character ('-').
    See:  https://en.wikipedia.org/wiki/Hostname#Restrictions_on_valid_hostnames
    You may assume there're no duplicates in url library.

*/

import common.HtmlParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _1236_Web_Crawler {

    private String getDomain(String url) {
        url = url.substring(7);
        int idx = url.indexOf("/");
        int to = (idx == -1) ? url.length() : idx;
        return url.substring(0, to);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> crawl_BFS(String startUrl, HtmlParser htmlParser) {
        String domain = getDomain(startUrl);
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(startUrl);
        while (!queue.isEmpty()) {
            String next = queue.poll();
            if (visited.add(next) && domain.equals(getDomain(next))) {
                result.add(next);
                for (String nei : htmlParser.getUrls(next)) {
                    queue.offer(nei);
                }
            }
        }
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> crawl_faster_BFS(String startUrl, HtmlParser htmlParser) {
        String domain = getDomain(startUrl);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(startUrl);
        visited.add(startUrl);
        while (!queue.isEmpty()) {
            String next = queue.poll();
            for (String nei : htmlParser.getUrls(next)) {
                if (domain.equals(getDomain(nei)) && visited.add(nei)) {
                    queue.offer(nei);
                }
            }
        }
        return new ArrayList<>(visited);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<String> crawl_DFS(String startUrl, HtmlParser htmlParser) {
        List<String> result = new ArrayList<>();
        dfs(result, new HashSet<>(), getDomain(startUrl), htmlParser, startUrl);
        return result;
    }

    private void dfs(List<String> result, Set<String> visited, String domain, HtmlParser htmlParser, String url) {
        if (visited.add(url) && domain.equals(getDomain(url))) {
            result.add(url);
            for (String nei : htmlParser.getUrls(url)) {
                dfs(result, visited, domain, htmlParser, nei);
            }
        }
    }

}
