/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/

/*
额，总算是写对了。。。开始很naive的以为直接一下找就可以了，但是考虑这个test case
[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
如果我直接找就会出现["JFK","KUL"]这种情况
所以dfs要遍历所有可能的情况，然后选出valid的那个
我比较傻的是，list怎么还原，突然不晓得怎么处理了。。。其实list.remove(list.size()-1);这样就好了，因为会一层层remove，一层层还原到原来的
这里根本不用再弄个什么什么copy的反而错了
然后注意dests.add(i,to);要add回原来的位置，而不能单纯的dests.add(i);
*/
public class Solution {
    public List<String> findItinerary(String[][] tickets) {
        List<String> list = new ArrayList<String>();
        if(tickets.length == 0) return list;
        
        Map<String, List<String>> map = new HashMap<>();
        for(String[] t : tickets) {
            if(!map.containsKey(t[0])) {
                map.put(t[0], new ArrayList<String>());
            }
            map.get(t[0]).add(t[1]);
        }
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        list.add("JFK");
        dfs(list, map, "JFK", tickets.length+1);
        return list;
    }
    
    public boolean dfs(List<String> list, Map<String, List<String>> map, String from, int len) {
        if(list.size() == len) return true;
        if(!map.containsKey(from) || map.get(from).size() == 0) return false;
        
        List<String> dests = map.get(from);
        for(int i = 0; i < dests.size(); i++) {
            String to = dests.get(i);
            dests.remove(i);
            list.add(to);
            if(dfs(list, map, to,len)) return true;    
            list.remove(list.size()-1);
            dests.add(i,to);
        }
        return false;
    }
}
