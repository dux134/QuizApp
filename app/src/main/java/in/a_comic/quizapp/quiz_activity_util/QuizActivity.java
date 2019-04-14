package in.a_comic.quizapp.quiz_activity_util;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import in.a_comic.quizapp.R;

public class QuizActivity extends AppCompatActivity {
    public static ArrayList<QuizActivityModel> list = new ArrayList<>();

    private AdView ad1,ad2;
    private TextView time,attempted,question;
    private RadioButton optionA,optionB,optionC,optionD;
    private int pos;

    public static int timer = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ImageView back = findViewById(R.id.quiz_activity_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        time = findViewById(R.id.current_textview);
        attempted = findViewById(R.id.attempted_textView);
        question = findViewById(R.id.question);
        optionA = findViewById(R.id.radioButtonA);
        optionB = findViewById(R.id.radioButtonB);
        optionC = findViewById(R.id.radioButtonC);
        optionD = findViewById(R.id.radioButtonD);

        pos = 0;
        int no = pos + 1;
        question.setText(no+". "+list.get(pos).getQuestion());
        optionA.setText(" "+list.get(pos).getOptionA());
        optionB.setText(" "+list.get(pos).getOptionB());
        optionC.setText(" "+list.get(pos).getOptionC());
        optionD.setText(" "+list.get(pos).getOptionD());

        final int minutes = 1;
        final int timeLimit = minutes *60*1000;
        CountDownTimer newtimer = new CountDownTimer(timeLimit, 1000) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                long minute = seconds/60;
                long second = seconds - (minute*60);
                time.setText("Auto submit in : " + minute+":"+second);

                if(minutes/1>minute)
                    time.setTextColor(Color.RED);
            }
            public void onFinish() {
                if(timer == 1)
                    submitAction();
            }
        };
        newtimer.start();
        attempted.setText("Attemped : 0/"+list.size());

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionA.isChecked()) {
                    optionA.setChecked(true);
                    optionB.setChecked(false);
                    optionC.setChecked(false);
                    optionD.setChecked(false);
                }else
                    optionA.setChecked(false);
            }
        });
        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionB.isChecked()) {
                    optionA.setChecked(false);
                    optionB.setChecked(true);
                    optionC.setChecked(false);
                    optionD.setChecked(false);
                }else
                    optionB.setChecked(false);
            }
        });
        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionC.isChecked()) {
                    optionA.setChecked(false);
                    optionB.setChecked(false);
                    optionC.setChecked(true);
                    optionD.setChecked(false);
                }else
                    optionC.setChecked(false);
            }
        });
        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (optionD.isChecked()) {
                    optionA.setChecked(false);
                    optionB.setChecked(false);
                    optionC.setChecked(false);
                    optionD.setChecked(true);
                } else
                    optionD.setChecked(false);
            }
        });


//        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");



        CardView submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timer == 1)
                    timer = 0;
                submitAction();
            }
        });
        CardView previous = findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos>0) {
                    updateAnswer(pos);
                    pos--;
                    int no = pos + 1;
                    question.setText(no+". "+list.get(pos).getQuestion());
                    optionA.setText(" "+list.get(pos).getOptionA());
                    optionB.setText(" "+list.get(pos).getOptionB());
                    optionC.setText(" "+list.get(pos).getOptionC());
                    optionD.setText(" "+list.get(pos).getOptionD());
                    updateRadiobutton(list.get(pos).getSelectedAnswer());
                    attempted.setText("Attemped : "+noOfAttemptedQuestion()+"/"+list.size());
                }
            }
        });
        CardView next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos<(list.size()-1)) {
                    updateAnswer(pos);
                    pos++;
                    int no = pos + 1;
                    question.setText(no+". "+list.get(pos).getQuestion());
                    optionA.setText(" "+list.get(pos).getOptionA());
                    optionB.setText(" "+list.get(pos).getOptionB());
                    optionC.setText(" "+list.get(pos).getOptionC());
                    optionD.setText(" "+list.get(pos).getOptionD());
                    updateRadiobutton(list.get(pos).getSelectedAnswer());

                    attempted.setText("Attemped : "+noOfAttemptedQuestion()+"/"+list.size());
                }
            }
        });
    }

    private void submitAction() {
        updateAnswer(pos);

        startActivity(new Intent(QuizActivity.this,QuizResult.class));
        finish();
    }

    private void updateRadiobutton(String selectedAnswer) {
        if(selectedAnswer.equals("A"))
            optionA.setChecked(true);
        if(selectedAnswer.equals("B"))
            optionB.setChecked(true);
        if(selectedAnswer.equals("C"))
            optionC.setChecked(true);
        if(selectedAnswer.equals("D"))
            optionD.setChecked(true);
    }

    private void updateAnswer(int pos) {
        list.get(pos).setSelectedAnswer(getAnswerSelected()+"");
        resetRadioButton();
    }

    private char getAnswerSelected() {
        if(optionA.isChecked())
            return 'A';
        if(optionB.isChecked())
            return 'B';
        if(optionC.isChecked())
            return 'C';
        if (optionD.isChecked())
            return 'D';
        return 'N';
    }

    private void resetRadioButton() {
        optionA.setChecked(false);
        optionB.setChecked(false);
        optionC.setChecked(false);
        optionD.setChecked(false);
    }

    private String noOfAttemptedQuestion() {
        int no = 0;
        for (QuizActivityModel lst:list) {
            if(!lst.getSelectedAnswer().equals("N"))
                no++;
        }
        return no+"";
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    static {
        list.add(new QuizActivityModel("question jlkjf kjhkj jjhjh kjhkjhkj kjhkj hkjh kjh kh kh khk jhk hkj hk hk hkj hkh kjh kh kjh kh kjh kjh kjh kh kh kh kjh kjh kjh kh kh kk  hkh kjh khk ","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
        list.add(new QuizActivityModel("question","A","B","C","D","A"));
    }
}