//https://www.luogu.org/problemnew/show/P3371
#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <vector>
using namespace std;
struct Edge{
    int to, len;
};
int n,m,s;
long long dist[10010];
int used[10010];
vector <Edge> val[10010];

void push(int from,int to,int len){
    val[from].push_back((Edge){to,len});
}

void Dijkstra(){
    for(int i=1;i<=n;i++) dist[i]=(i==s?0:2147483647);
    int now = s;
    while(now>=0){
        used[now] = 1;
        for(int i=0;i<val[now].size();++i){
            if(dist[val[now][i].to]>dist[now]+val[now][i].len)
                dist[val[now][i].to] = dist[now] + val[now][i].len;
        }
        int mins = -1;
        for(int j=1;j<=n;++j){
            if(used[j]==0&&(mins<0||dist[j]<dist[mins]))
                mins = j;
        }
        now = mins;
    }
}

int main(){
    int from,to,len;
    cin >> n >> m >> s;
    for(int i=0;i<m;++i){
        cin >> from >> to >> len;
        push(from,to,len);
    }
    Dijkstra();
    for(int i=1;i<=n;i++){
        if(i<n) cout << dist[i] << " ";
        else cout << dist[i] << endl;
    }
}
