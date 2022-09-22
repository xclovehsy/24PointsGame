package com.example.a24pointsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "leo";
    private String curCardName = "";
    private String welcome = "请选择4张卡片，并通过加减乘除计算得到24点!!";
    private int curCardBackId = 0;
    private int curCardNum = 0;
    private int curCardId = 0;
    private final Point24 point24 = new Point24();
    private int[] chooseNums = {0, 0, 0, 0};
    private ImageButton card1 = null;
    private ImageButton card2 = null;
    private ImageButton card3 = null;
    private ImageButton card4 = null;
//    private TextView tips = null;
    private List<ImageButton> chooseCards = new ArrayList<ImageButton>();
    private String[] chooseCardsName ={"", "", "", ""};
    private Stack<Integer> sta = new Stack<Integer>();
    private Stack<ImageButton> sta2 = new Stack<ImageButton>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        chooseCards.add(card1);
        chooseCards.add(card2);
        chooseCards.add(card3);
        chooseCards.add(card4);

//        tips = findViewById(R.id.tips);
//        tips.setText(welcome);

    }

    /**
     * 获取24点表达式
     * @param view
     */
    public void getTips(View view){

        // 获取24点表达式
        StringBuilder msg = new StringBuilder();
        boolean isHave = false;
        if(chooseNums[0] == 0 || chooseNums[1] == 0 || chooseNums[2] == 0 || chooseNums[3] == 0){
//            tips.setText("请先选择4张卡片");
            msg = new StringBuilder("请先选择4张卡片。");
        }else{
//            tips.setText(point24.getPoint24Equ(chooseNums).toString());
            List<String> equs = point24.getPoint24Equ(chooseNums);
            if(equs.isEmpty()){
                msg = new StringBuilder("当前选择的四张卡片不能构成24点表达式。");
            }else{
                int cnt = equs.size();
                for(int i = 0; i<cnt; i++){
                    msg.append(i+1).append("、").append(equs.get(i)).append("\n");
                }
                msg.append("共有 "+cnt+" 个表达式");
                isHave = true;
            }
        }
        Drawable icon = null;
        if(isHave){
            icon = getResources().getDrawable(R.drawable.ok);
        }else{
            icon = getResources().getDrawable(R.drawable.alert);
        }


        // 创建弹窗
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(icon)
                .setTitle("24点表达式")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("清空", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 清空卡牌框
                        Drawable quesBack = getResources().getDrawable(R.drawable.question);
                        card1.setBackground(quesBack);
                        card2.setBackground(quesBack);
                        card3.setBackground(quesBack);
                        card4.setBackground(quesBack);

                        // 清空数组
                        chooseNums[0] = chooseNums[1] = chooseNums[2] = chooseNums[3] = 0;
                        chooseCardsName[0] = chooseCardsName[1] = chooseCardsName[2] = chooseCardsName[3] = "";
                        sta.clear();
//                        sta2.clear();
                        while(!sta2.empty()){
                            ImageView bnt = sta2.pop();
                            bnt.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .setMessage(msg.toString())
                .create()
                .show();
    }
    /**
     * 清空输入的卡片以及运算符
     * @param view
     */
    public void clearCards(View view){
        // 清空卡牌框
        Drawable quesBack = getResources().getDrawable(R.drawable.question);
        card1.setBackground(quesBack);
        card2.setBackground(quesBack);
        card3.setBackground(quesBack);
        card4.setBackground(quesBack);

        // 清空数组
        chooseNums[0] = chooseNums[1] = chooseNums[2] = chooseNums[3] = 0;
        chooseCardsName[0] = chooseCardsName[1] = chooseCardsName[2] = chooseCardsName[3] = "";
        sta.clear();
        while(!sta2.empty()){
            ImageView bnt = sta2.pop();
            bnt.setVisibility(View.VISIBLE);
        }
//        tips.setText(welcome);
    }

    /**
     * 回退卡片
     * @param view
     */
    public void backCards(View view){
        if(!sta.empty()){
            int index = sta.pop();
            chooseCards.get(index).setBackground(getResources().getDrawable(R.drawable.question));
            chooseNums[index] = 0;
            chooseCardsName[index] = "";
        }
        if(!sta2.empty()){
            ImageButton bnt = sta2.pop();
            bnt.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 点击选择按钮
     * @param view
     */
    public void insertCard(View view) {
        ImageButton bnt = findViewById(R.id.card1);

        // 判断是在哪一个空格
        if (view.getId() == R.id.card1) {
            chooseNums[0] = curCardNum;
        } else if (view.getId() == R.id.card2) {
            chooseNums[1] = curCardNum;
        } else if (view.getId() == R.id.card3) {
            chooseNums[2] = curCardNum;
        } else if (view.getId() == R.id.card4) {
            chooseNums[3] = curCardNum;
        }

        // 更换背景
        view.setBackground(getResources().getDrawable(curCardBackId));

        Log.e(TAG, "insertCard: " + chooseNums[0] + " " + chooseNums[1] + " " + chooseNums[2] + " " + chooseNums[3]);
    }

    /**
     * 根据卡片名称获取点数
     * @param cardName
     * @return
     */
    public int getCardNum(String cardName) {
        int num = 0;
        switch (cardName.charAt(cardName.length() - 1)) {
            case 'a':
                num = 1;
                break;
            case '2':
                num = 2;
                break;
            case '3':
                num = 3;
                break;
            case '4':
                num = 4;
                break;
            case '5':
                num = 5;
                break;
            case '6':
                num = 6;
                break;
            case '7':
                num = 7;
                break;
            case '8':
                num = 8;
                break;
            case '9':
                num = 9;
                break;
            case '0':
                num = 10;
                break;
            case 'j':
                num = 11;
                break;
            case 'q':
                num = 12;
                break;
            case 'k':
                num = 13;
                break;
            default:
                num = 0;
                break;
        }
        return num;

    }

    /**
     * 选择卡片
     * @param view
     */
    @SuppressLint("NonConstantResourceId")
    public void chooseCard(View view) {

        curCardId = view.getId();

        // 获取当前选取牌的名称
        switch (view.getId()) {
            case R.id.cluba:  curCardBackId = R.drawable.cluba; curCardName = "cluba"; break;
            case R.id.club2:  curCardBackId = R.drawable.club2; curCardName = "club2"; break;
            case R.id.club3:  curCardBackId = R.drawable.club3; curCardName = "club3"; break;
            case R.id.club4:  curCardBackId = R.drawable.club4; curCardName = "club4"; break;
            case R.id.club5:  curCardBackId = R.drawable.club5; curCardName = "club5"; break;
            case R.id.club6:  curCardBackId = R.drawable.club6; curCardName = "club6"; break;
            case R.id.club7:  curCardBackId = R.drawable.club7; curCardName = "club7"; break;
            case R.id.club8:  curCardBackId = R.drawable.club8; curCardName = "club8"; break;
            case R.id.club9:  curCardBackId = R.drawable.club9; curCardName = "club9"; break;
            case R.id.club10:  curCardBackId = R.drawable.club10; curCardName = "club10"; break;
            case R.id.clubj:  curCardBackId = R.drawable.clubj; curCardName = "clubj"; break;
            case R.id.clubq:  curCardBackId = R.drawable.clubq; curCardName = "clubq"; break;
            case R.id.clubk:  curCardBackId = R.drawable.clubk; curCardName = "clubk"; break;
            case R.id.hearta:  curCardBackId = R.drawable.hearta; curCardName = "hearta"; break;
            case R.id.heart2:  curCardBackId = R.drawable.heart2; curCardName = "heart2"; break;
            case R.id.heart3:  curCardBackId = R.drawable.heart3; curCardName = "heart3"; break;
            case R.id.heart4:  curCardBackId = R.drawable.heart4; curCardName = "heart4"; break;
            case R.id.heart5:  curCardBackId = R.drawable.heart5; curCardName = "heart5"; break;
            case R.id.heart6:  curCardBackId = R.drawable.heart6; curCardName = "heart6"; break;
            case R.id.heart7:  curCardBackId = R.drawable.heart7; curCardName = "heart7"; break;
            case R.id.heart8:  curCardBackId = R.drawable.heart8; curCardName = "heart8"; break;
            case R.id.heart9:  curCardBackId = R.drawable.heart9; curCardName = "heart9"; break;
            case R.id.heart10:  curCardBackId = R.drawable.heart10; curCardName = "heart10"; break;
            case R.id.heartj:  curCardBackId = R.drawable.heartj; curCardName = "heartj"; break;
            case R.id.heartq:  curCardBackId = R.drawable.heartq; curCardName = "heartq"; break;
            case R.id.heartk:  curCardBackId = R.drawable.heartk; curCardName = "heartk"; break;
            case R.id.spadea:  curCardBackId = R.drawable.spadea; curCardName = "spadea"; break;
            case R.id.spade2:  curCardBackId = R.drawable.spade2; curCardName = "spade2"; break;
            case R.id.spade3:  curCardBackId = R.drawable.spade3; curCardName = "spade3"; break;
            case R.id.spade4:  curCardBackId = R.drawable.spade4; curCardName = "spade4"; break;
            case R.id.spade5:  curCardBackId = R.drawable.spade5; curCardName = "spade5"; break;
            case R.id.spade6:  curCardBackId = R.drawable.spade6; curCardName = "spade6"; break;
            case R.id.spade7:  curCardBackId = R.drawable.spade7; curCardName = "spade7"; break;
            case R.id.spade8:  curCardBackId = R.drawable.spade8; curCardName = "spade8"; break;
            case R.id.spade9:  curCardBackId = R.drawable.spade9; curCardName = "spade9"; break;
            case R.id.spade10:  curCardBackId = R.drawable.spade10; curCardName = "spade10"; break;
            case R.id.spadej:  curCardBackId = R.drawable.spadej; curCardName = "spadej"; break;
            case R.id.spadeq:  curCardBackId = R.drawable.spadeq; curCardName = "spadeq"; break;
            case R.id.spadek:  curCardBackId = R.drawable.spadek; curCardName = "spadek"; break;
            case R.id.diamonda:  curCardBackId = R.drawable.diamonda; curCardName = "diamonda"; break;
            case R.id.diamond2:  curCardBackId = R.drawable.diamond2; curCardName = "diamond2"; break;
            case R.id.diamond3:  curCardBackId = R.drawable.diamond3; curCardName = "diamond3"; break;
            case R.id.diamond4:  curCardBackId = R.drawable.diamond4; curCardName = "diamond4"; break;
            case R.id.diamond5:  curCardBackId = R.drawable.diamond5; curCardName = "diamond5"; break;
            case R.id.diamond6:  curCardBackId = R.drawable.diamond6; curCardName = "diamond6"; break;
            case R.id.diamond7:  curCardBackId = R.drawable.diamond7; curCardName = "diamond7"; break;
            case R.id.diamond8:  curCardBackId = R.drawable.diamond8; curCardName = "diamond8"; break;
            case R.id.diamond9:  curCardBackId = R.drawable.diamond9; curCardName = "diamond9"; break;
            case R.id.diamond10:  curCardBackId = R.drawable.diamond10; curCardName = "diamond10"; break;
            case R.id.diamondj:  curCardBackId = R.drawable.diamondj; curCardName = "diamondj"; break;
            case R.id.diamondq:  curCardBackId = R.drawable.diamondq; curCardName = "diamondq"; break;
            case R.id.diamondk:  curCardBackId = R.drawable.diamondk; curCardName = "diamondk"; break;
            default: curCardBackId = 0; curCardName = ""; break;
        }

        curCardNum = getCardNum(curCardName);

        Log.e(TAG, "chooseCard: " + curCardName);
        Log.e(TAG, "chooseCard: " + curCardNum);


        // 判断之前有没有选择该张卡片
        for(int i = 0; i<4; i++){
            if(chooseCardsName[i].equals(curCardName)) return;
        }

        //从四个空格中选择一个
        for(int i = 0; i<4; i++){
            if(chooseNums[i] == 0){
                sta.push(i);
//                ImageButton bnt = ;
                sta2.push(findViewById(curCardId));
                view.setVisibility(View.INVISIBLE);
                chooseCards.get(i).setBackground(getResources().getDrawable(curCardBackId));
                chooseNums[i] = curCardNum;
                chooseCardsName[i] = curCardName;
                break;
            }
        }
    }
}