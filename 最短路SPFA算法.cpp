//代码丑求不被嫌弃
#include<iostream>
#include<cstring>
#include<cstdio>
#include<cstdlib>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;

struct trem{
    int to,len;
};

int n,m;
int dist[100100];       //储存最短路距离
bool used[100100];      //节点是否在队列中的标记
vector<trem> team[100100];     //邻接表
queue<int> val;         //列表

void insert(int from,int to,int len){       //将输入的数据插入邻接表
    team[from].push_back((trem){to,len});
}

void spfa(){                    //SPFA
    memset(dist,0x3f,sizeof(dist));     //几个初始化操作
    val.push(root);
    dist[val.front()]=0;
    used[val.front()]=true;

    while(!val.empty()){
        int head = val.front();val.pop();
        used[head] = false;
        for(int i=0;i<team[head].size();++i){       //遍历某节点的所有链接，循环更新出更小的距离(执行松弛操作)
            if(dist[head] + team[head][i].len < dist[team[head][i].to]){
                dist[team[head][i].to] = dist[head] + team[head][i].len;      //更新
                if(!used[team[head][i].to]){
                    val.push(team[head][i].to);
                    used[head] = true;
                }
            }
        }
    }
}

int main(){
    int a,b,c;
    cin >> n >> m;
    for(int i=0;i<m;++i){
        cin >> a >> b >> c;
        insert(a,b,c);
    }
    for(int j=0;j<n;++j) cout << dist[j] << " ";
    return 0;
}
