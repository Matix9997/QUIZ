package pl.edu.pb.quiz5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int correctAnswers =0;
    private int wrongAnswers=0;
    private boolean answered=false;
    private Question[] questions = new Question[] {
            new Question(R.string.Blyskawica,true),
            new Question(R.string.Watykan,true),
            new Question(R.string.Melbourne,false),
            new Question(R.string.Fuji,true),
            new Question(R.string.Brokuly,true)
    };
    private int currentIndex=0;

    private void CheckAnswerCorrectness(boolean userAnswer){
        if(!answered){
            boolean correctAnswer = questions[currentIndex].isTrueAnswer();
            int resultMessageId=0;
            if(userAnswer==correctAnswer) {
                resultMessageId=R.string.correct_answer;
                correctAnswers++;
                answered=true;
            }
            else {
                resultMessageId=R.string.incorrect_answer;
                wrongAnswers++;
                answered=true;
            }
            Toast.makeText(this,resultMessageId,Toast.LENGTH_SHORT).show();
        }
        
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton=findViewById(R.id.true_button);
        falseButton=findViewById(R.id.false_button);
        nextButton=findViewById(R.id.next_button);
        questionTextView=findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckAnswerCorrectness(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CheckAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(!answered) wrongAnswers++;
                answered=false;
                currentIndex=(currentIndex+1)%questions.length;
                setNextQuestion();
                checkIfEnded();
            }
        });
        setNextQuestion();
    }
    private void checkIfEnded(){

        if(currentIndex%questions.length==0){
            String resultMessage = "Uzyskałeś "+ correctAnswers+" dobrych odpowiedzi oraz "+wrongAnswers+" złych";
            Toast.makeText(this,resultMessage,Toast.LENGTH_SHORT).show();
            correctAnswers=0;
            wrongAnswers=0;
        }
    }
    private void setNextQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }


}
