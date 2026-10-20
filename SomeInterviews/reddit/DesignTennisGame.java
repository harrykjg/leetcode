package SomeInterviews.reddit;

public class DesignTennisGame {
    /*
    A tennis scoring system is needed to track a single game played between two players, A and B. Points are awarded one at a time to a player, and the score must be tracked according to real tennis rules, including "deuce", "advantage", and game-winning logic. Scores must be accessible in both numeric and standard tennis notation.

Implement the TennisGame class:

TennisGame() Initializes a new game. Both players start at 0 points, and there is no winner.
void pointTo(char player) Awards one point to the given player ('A' or 'B'). If the game has already finished (i.e., there is a winner), further calls to this method must have no effect.
String getScore() Returns the current numeric score as a string in the format "A:B", representing player A and B's points.

When both players have at least 3 points, and their scores are equal, the output must be clamped to "3:3" regardless of the actual internal points. For example, internal scores of 4:4 or 5:5 must be reported as "3:3".
char getWinner() Returns 'A' or 'B' if that player has won the game, or '#' if no winner yet.

A player wins if they have at least 4 points and lead the opponent by at least 2 points.

Constraints:

All calls to pointTo use valid player values ('A' or 'B').
The game state is guaranteed to be valid per tennis rules.
0 ≤ both players' point totals throughout the game.
Example

Input:
["TennisGame", "getScore", "pointTo", "getScore", "pointTo", "getScore", "pointTo", "getScore", "pointTo", "getScore", "getWinner", "pointTo", "getScore", "getWinner"]
[[], [], ['A'], [], ['B'], [], ['A'], [], ['A'], [], [], ['A'], [], []]

Output:
[null, "0:0", null, "1:0", null, "1:1", null, "2:1", null, "3:1", "#", null, "4:1", "A"]

Explanation:

TennisGame game = new TennisGame();
game.getScore(); // Return "0:0".
game.pointTo('A');
game.getScore(); // Return "1:0".
game.pointTo('B');
game.getScore(); // Return "1:1".
game.pointTo('A');
game.getScore(); // Return "2:1".
game.pointTo('A');
game.getScore(); // Return "3:1".
game.getWinner(); // Return "#", since no winner yet.
game.pointTo('A');
game.getScore(); // Return "4:1".
game.getWinner(); // Return "A".
     */
    //看着简单还真不好做
    // 关键是3：3之后，如果再平手如4：4，5：5则都一律把分出重置成3：3. 如果有一方多分才显示正常分入4：3，如果又变成4：4则再重置成3：3，如果变成
    //5：3则就是显示5：3.
    //还有，如果已经赢了如4：0，则b再加分，则还是显示4：0
    //答案说直接再pointto的时候就检测是否获胜了，不是说getwinner的时候才去检测
    class TennisGame {
        private int scoreA;
        private int scoreB;
        private char winner; // '#' 表示尚无胜者

        public TennisGame() {
            scoreA = 0;
            scoreB = 0;
            winner = '#';
        }

        public void pointTo(char player) {
            if (winner != '#') {
                return; // 游戏已结束，忽略后续得分
            }
            if (player == 'A') {
                scoreA++;
            } else {
                scoreB++;
            }
            // 检查是否有玩家获胜
            if (scoreA >= 4 || scoreB >= 4) {
                if (scoreA - scoreB >= 2) {
                    winner = 'A';
                } else if (scoreB - scoreA >= 2) {
                    winner = 'B';
                }
            }
            //不能这样修改score，显示分数要用后面得偏移量
//            if (scoreA == scoreB && scoreA >= 3) {
//                scoreA = 3;
//                scoreB = 3;
//            }
        }

        public String getScore() {
            //这样就行了，他答案的不好
            if(scoreA==scoreB&&scoreA>=3){
                return "3:3";
            }
            return scoreA + ":" + scoreB;

        }

        public char getWinner() {
            return winner;
        }
    }
    /*
    Follow-up:
Extending the tennis scoring system, you are now required to model a tennis set composed of multiple games between two players, 'A' and 'B'. The set follows a best of 5 rule: the first player to win 3 games wins the set.

Each game follows traditional tennis scoring rules as described in the previous question. Your implementation must manage the flow of games within the set, track scores, and enforce the winning and termination conditions.

Implement the TennisSet class:

TennisSet() Initializes a new tennis set. Both players start with 0 game wins, and the first game is automatically started.

void pointTo(char player) Adds one point to the current game for the given player ('A' or 'B').

If the current game ends, the winner's game count is incremented, and the next game is automatically started if neither player has won the set yet.
If the set is already finished, further calls to this method have no effect.
String getScore() Returns the current game score as a string in the format "scoreA:scoreB", using the same display rules as described earlier. The score clamping rule remains in effect.

String getSetScore() Returns the number of games won by each player as a string in the format "gameA:gameB".

char getSetWinner() Returns 'A' or 'B' if a player has already won 3 games in the set, or '#' if the set is not finished.

Constraints:

All calls to pointTo will use valid player values ('A' or 'B').
A set consists of at most 5 games.
Each individual game is managed using the TennisGame interface and rules.
1 ≤ number of method calls ≤ 10⁴.
Example

Input:
["TennisSet", "pointTo", "pointTo", "pointTo", "pointTo", "getSetScore", "getScore", "pointTo", "pointTo", "pointTo", "pointTo", "pointTo", "getSetScore", "pointTo", "pointTo", "pointTo", "pointTo", "getSetScore", "getSetWinner"]
[[], ['A'], ['A'], ['A'], ['A'], [], [], ['A'], ['A'], ['B'], ['A'], ['A'], [], ['A'], ['A'], ['A'], ['A'], [], []]

Output:
[null, null, null, null, null, "1:0", "0:0", null, null, null, null, null, "2:0", null, null, null, null, "3:0", "A"]

Explanation:

TennisSet set = new TennisSet();
set.pointTo('A');
set.pointTo('A');
set.pointTo('A');
set.pointTo('A'); // Player A wins the first game.
set.getSetScore(); // Return "1:0" after Player A wins the first game.
set.getScore(); // Return "0:0" because a new game has started.
set.pointTo('A');
set.pointTo('A');
set.pointTo('B');
set.pointTo('A');
set.pointTo('A'); // Player A wins the second game.
set.getSetScore(); // Return "2:0".
set.pointTo('A');
set.pointTo('A');
set.pointTo('A');
set.pointTo('A'); // Player A wins the third game and the set.
set.getSetScore(); // Return "3:0".
set.getSetWinner(); // Return 'A'.

Hint
Separate the internal state into two distinct components: one for managing points within a single game and another for tracking games won across the set, triggering transitions only when specific win
     */

    class TennisSet {
        int gameWinA;
        int gameWinB;
        int scoreA;
        int scoreB;
        int gameCount=0;
        public TennisSet() {
            // TODO: Initialize TennisSet
            gameCount=0;
            gameWinA=0;
            gameWinB=0;
            scoreA=0;
            scoreB=0;
        }

        public void pointTo(char player) {
            // TODO: Implement pointTo logic
            if(isOver()){
                return;
            }
            if(smallOver()){
                scoreA=0;
                scoreB=0;
            }
            if(player=='A'){
                scoreA++;
            }else{
                scoreB++;
            }
            if(smallOver()){
                if(scoreA>scoreB){
                    gameWinA++;
                }else{
                    gameWinB++;
                }
                scoreA=0;
                scoreB=0;
            }
        }

        public String getScore() {
            // TODO: Implement getScore logic
            if(scoreA==scoreB&&scoreA>=3){
                return "3:3";
            }
            return scoreA+":"+scoreB;
        }

        public String getSetScore() {
            // TODO: Implement getSetScore logic
            return gameWinA+":"+gameWinB;
        }

        public char getSetWinner() {
            // TODO: Implement getSetWinner logic
            if(gameWinA>=3){
                return 'A';
            }else if(gameWinB>=3){
                return 'B';
            }
            return '#';
        }

        public boolean isOver() {
            if((gameWinA>=3||gameWinB>=3)){
                return true;
            }
            return false;
        }
        //小局的结束
        public boolean smallOver() {
            // TODO: Implement isOver logic
            if((scoreA>=4||scoreB>=4)&&Math.abs(scoreA-scoreB)>1){
                return true;
            }
            return false;
        }
/*
## Follow-up
转 human score：

```
love
deuce
advantage

内部分数 (A:B)	Human Score
0:0	Love:Love
1:0	15:Love
2:1	30:15
3:2	40:30
3:3	Deuce
4:3	Advantage A
4:4	Deuce
5:4	Advantage A
5:5	Deuce
6:5	Advantage A
     */
    public String getScore() {
    // Deuce / Advantage
    if (scoreA >= 3 && scoreB >= 3) {
        if (scoreA == scoreB) {
            return "Deuce";
        } else if (scoreA > scoreB) {//没有5：3这样的，那样就已经赢了
            return "Advantage A";
        } else {
            return "Advantage B";
        }
    }
//love就是0分
    String[] scores = {"Love", "15", "30", "40"};

    return scores[scoreA] + ":" + scores[scoreB];
}

    }



}
