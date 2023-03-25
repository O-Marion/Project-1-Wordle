package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*

class WordleActivity : AppCompatActivity() {
    var numberOfGuesses = 0
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var isGuessCorrect = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wordle)
        //Toast.makeText(this, wordToGuess,Toast.LENGTH_LONG).show()
        val guessWord1 = findViewById<TextView>(R.id.tv_guessWord1)
        val guessWord2 = findViewById<TextView>(R.id.tv_guessWord2)
        val guessWord3 = findViewById<TextView>(R.id.tv_guessWord3)
        val guessWord1Check = findViewById<TextView>(R.id.tv_guessWord1Check)
        val guessWord2Check = findViewById<TextView>(R.id.tv_guessWord2Check)
        val guessWord3Check = findViewById<TextView>(R.id.tv_guessWord3Check)
        val wordOfDay = findViewById<TextView>(R.id.tv_wordOfDay)

        val buttonText = findViewById<Button>(R.id.buttonText)
        buttonText.setText("Guess")
        var editText = findViewById<EditText>(R.id.editText)
        buttonText.setOnClickListener {
            val inputFromUser = editText.text

            if (numberOfGuesses == 0){
                /*  This is the first guess
                    Increment numberOfGuesses by 1
                    Update tv_guessWord1.
                    Check Correctness of the guess.
                    Update tv_guessWordCheck1 with correctness
                */
                numberOfGuesses++
                guessWord1.text = inputFromUser
                val guessCorrectness = checkGuess(inputFromUser.toString().uppercase(Locale.getDefault()), wordToGuess)
                guessWord1Check.text = guessCorrectness
                if (guessCorrectness == "OOOO"){
                    isGuessCorrect = true
                    wordOfDay.text = wordToGuess
                    Toast.makeText(this, "Congratulations!! You guessed the correct word!!",Toast.LENGTH_SHORT).show()
                }
            }else if(numberOfGuesses == 1){
                /*  This is the second guess
                    Increment numberOfGuesses by 1
                    Update tv_guessWord2.
                    Check Correctness of the guess.
                    Update tv_guessWordCheck2 with correctness
                */
                numberOfGuesses++
                guessWord2.text = inputFromUser
                val guessCorrectness = checkGuess(inputFromUser.toString().uppercase(Locale.getDefault()), wordToGuess)
                guessWord2Check.text = guessCorrectness
                if (guessCorrectness == "OOOO"){
                    isGuessCorrect = true
                    wordOfDay.text = wordToGuess
                    Toast.makeText(this, "Congratulations!! You guessed the correct word!!",Toast.LENGTH_SHORT).show()
                }
            }else if(numberOfGuesses == 2){
                /*  This is the third guess
                    Increment numberOfGuesses by 1
                    Update tv_guessWord3.
                    Check Correctness of the guess.
                    Update tv_guessWordCheck3 with correctness
                */
                numberOfGuesses++
                guessWord3.text = inputFromUser
                val guessCorrectness = checkGuess(inputFromUser.toString().uppercase(Locale.getDefault()), wordToGuess)
                guessWord3Check.text = guessCorrectness
                wordOfDay.text = wordToGuess
                if (guessCorrectness == "OOOO"){
                    isGuessCorrect = true
                    Toast.makeText(this, "Congratulations!! You guessed the correct word!!",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Better luck next time.",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "You can guess only 3 times.",Toast.LENGTH_SHORT).show()
            }
            editText.setText("")
            //textView.text = inputFromUser

        }

    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

}