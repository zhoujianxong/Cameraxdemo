package com.blue.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.SparseIntArray;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.UnaryOperator;

public class WordsActivity extends AppCompatActivity {
    private final String TAG = WordsActivity.class.getSimpleName();
    private TextView textView;
    List<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        textView = findViewById(R.id.main_tv);
        way();
    }

    private void way() {
        words.add("8");
        words.add("The");
        words.add("security");
        words.add("flaw");
        words.add("exactly");
        words.add("have");
        words.add("password");
        words.add("line");
        words.add("combination");
        words.add("same");
        words.add("were");
        words.add("thought");
        words.add("change");

        SpannableString spannableString = new SpannableString(textView.getText().toString());
        List<String> listStartEnd = getWordIndex(textView.getText().toString(), words);
        for (int i = 0; i < listStartEnd.size(); i++) {
            int start = Integer.valueOf(listStartEnd.get(i).split("#")[0]);
            int end = Integer.valueOf(listStartEnd.get(i).split("#")[1]);

            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark)), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            //设置字体样式: NORMAL正常，BOLD粗体，ITALIC斜体，BOLD_ITALIC粗斜体
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        textView.setText(spannableString);

        List<String> list = getStringList(textView.getText().toString());
        for (String s : list) {
            Log.v(TAG, "---" + s);
        }
    }


    /**
     * 获取单词的下标
     * @param res
     * @param words
     * @return
     */
    public static List<String> getWordIndex(String res, List<String> words) {
        List<String> listStartEnd = new ArrayList<>();
        List<String> resWordList = getStringList(res);
        int s = 0;
        for (int k = 0; k < resWordList.size(); k++) {
            //resWord 分割出来的单词
            String resWord = resWordList.get(k);
            for (int i = 0; i < words.size(); i++) {
                //word 等级单词
                String word = words.get(i);
                if (word != null && resWord != null) {
                    //判断是否相等  包含大小写
                    if (word.equalsIgnoreCase(resWord)) {
                        int startIndex = res.indexOf(resWord, s);
                        listStartEnd.add(startIndex + "#" + (startIndex + resWord.length()));
                        break;
                    }
                }
            }
            s = res.indexOf(resWord, s) + resWord.length();
        }
        return listStartEnd;
    }

    /**
     * 切割单词
     * @param res
     * @return
     */
    public static List<String> getStringList(String res) {
        //空格切割
        String[] blankSpace = res.split(" ");
        List<String> list = new ArrayList<>(Arrays.asList(blankSpace));
        return getSplit(list, getSplitList());
    }

    /**
     * 切割条件
     * @return
     */
    private static List<String> getSplitList() {
        List<String> splitList = new ArrayList<>();
        splitList.add("\t");
        splitList.add("\n");
        //英文标点
        splitList.add("\"");
        splitList.add("\'");
        splitList.add(",");
        splitList.add(".");
        splitList.add(";");
        splitList.add(":");
        splitList.add("?");
        splitList.add("!");
        splitList.add("...");
        splitList.add("-");
        splitList.add("--");
        splitList.add("---");
        splitList.add("(");
        splitList.add(")");
        splitList.add("[");
        splitList.add("]");
        splitList.add("{");
        splitList.add("}");
        splitList.add("<");
        splitList.add(">");

        //特殊符号
        splitList.add("@");
        splitList.add("#");
        splitList.add("$");
        splitList.add("%");
        splitList.add("^");
        splitList.add("&");
        splitList.add("*");

        //中文标点 为防止内容组写错  包含
        splitList.add("，");
        splitList.add("。");
        splitList.add("？");
        splitList.add("！");
        splitList.add("、");
        splitList.add("；");
        splitList.add("：");
        splitList.add("“");
        splitList.add("”");
        splitList.add("’");
        splitList.add("‘");
        splitList.add("【");
        splitList.add("】");
        splitList.add("——");
        splitList.add("-");
        splitList.add("--");
        splitList.add("~");
        splitList.add("·");
        splitList.add("《");
        splitList.add("》");
        return splitList;
    }


    /**
     * 获取分割单词  list
     *
     * @param list
     * @param splitList
     * @return
     */
    private static List<String> getSplit(List<String> list, List<String> splitList) {
        //空格切割
        List<String> returnli = new ArrayList<>(list);
        for (int k = 0; k < splitList.size(); k++) {
            String split = splitList.get(k);
            //操作一个新的 list
            List<String> setlist = new ArrayList<>(returnli);
            for (int i = 0; i < returnli.size(); i++) {
                String word = returnli.get(i);
                if (word != null) {
                    if (word.contains(split)) {
                        StringBuilder stringBuilder=new StringBuilder("\\");
                        stringBuilder.append(split);
                        String[] fullStop = returnli.get(i).split(stringBuilder.toString());
                        List<String> fullStopList = new ArrayList<>(Arrays.asList(fullStop));
                        setlist.addAll(i, fullStopList);
                        //清除原来的 string
                        setlist.remove(i + fullStopList.size());
                    }
                }
            }
            returnli = new ArrayList<>(setlist);
        }
        return returnli;
    }

}
