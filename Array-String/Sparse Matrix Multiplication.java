/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/*
开始有点忘记matrix multiplication怎么做了
是这样的A [m][n] B [n][p] - > res[m][p]
res[i][j] = sum(k=0~n-1) A[i][k] * B[k][p]

因为是sparse的嘛，所以用hashmap比较方便
开始想着pair就是(i,j) 然后看看在不在里面

但是我后来遍历的时候，很难check一个pair在不在里面，因为是object嘛。。。
所以我后来就改为了mapA - i对应一个list of pair，pair里面存j和A[i][j]的值，B同理

但是时间复杂度还是不好，莫非我要map套map？
*/

public class Solution {
    class Pair {
        int index;
        int value;
        private Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public int[][] multiply(int[][] A, int[][] B) {
        Map<Integer, List<Pair>> mapA = new HashMap<>();
        Map<Integer, List<Pair>> mapB = new HashMap<>();
        
        int m = A.length, n = A[0].length, p = B[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j ++) {
                if(A[i][j] != 0) {
                    if(!mapA.containsKey(i)) mapA.put(i, new ArrayList<Pair>());
                    mapA.get(i).add(new Pair(j, A[i][j]));
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < p; j++) {
                if(B[i][j] != 0) {
                    if(!mapB.containsKey(j)) mapB.put(j, new ArrayList<Pair>());
                    mapB.get(j).add(new Pair(i, B[i][j]));
                }
            }
        }
        int[][] res = new int[m][p];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < p; j++) {
                
                if(mapA.containsKey(i) && mapB.containsKey(j)) {
                    List<Pair> listA = mapA.get(i);
                    List<Pair> listB = mapB.get(j);
                    
                    int inA = 0, inB = 0;
                    while(inA < listA.size() && inB < listB.size()) {
                        Pair pA = listA.get(inA), pB = listB.get(inB);
                        if(pA.index == pB.index) {
                            res[i][j] += pA.value * pB.value;
                            inA ++;
                            inB ++;
                        } else if (pA.index < pB.index) {
                            inA ++;
                        } else {
                            inB ++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
