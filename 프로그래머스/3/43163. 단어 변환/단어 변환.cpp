#include <string>
#include <vector>

using namespace std;

string tar;
int depth = 0;

void dfs(vector<string>& words, vector<string>& stack, string curString, int curDepth) {
    // || depth <= curDepth => Backtracking
    if (curDepth >= words.size() && curString != tar || depth <= curDepth) {
        return;
    }

    if (curString == tar && depth > curDepth) {
        depth = curDepth;
        return;
    }
    // check words already exist in stack
    for (auto w : words) {
        for (auto s : stack) {
            if (w == s) {
                break;
            }
        }
        // check words are only different one char
        int notMatch = 0;
        for (int i = 0; i < w.length(); i++) {
            if (w[i] != curString[i]) {
                notMatch++;
            }
        }

        // DFS
        if(notMatch == 1) {
            stack.push_back(w);
            dfs(words, stack, w, curDepth + 1);
            stack.pop_back();
        }
    }

}

int solution(string begin, string target, vector<string> words) {
    // stack for saving steps
    vector<string> stack;
    tar = target;
    depth = words.size() + 1;

    dfs(words, stack, begin, 0);

    if (depth == words.size() + 1) {
        return 0;
    }

    return depth;
}