package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import com.example.lab3.databinding.ActivityMainBinding
import kotlin.math.roundToInt


enum class Operation {
    Plus,
    Minus,
    Multiply,
    Divide,
    Equals,
    Digit,
    Precent,
    Comma
}
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var num1=0.0
    private var previousOperation: Operation? = null
    private var previousForDigits:Operation?=null
    private var num2=0.0
    private var result=""
//    private var answer_num:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}

        fun OnButtonZeroClick(view:View){
            addDigit(0)
        }
        fun onButtonOneClick(view: View) {
            addDigit(1)
        }
        fun onButtonTwoClick(view: View) {
            addDigit(2)
        }
        fun onButtonThreeClick(view: View) {
            addDigit(3)
        }
        fun onButtonForClick(view: View) {
            addDigit(4)
        }
        fun onButtonFiveClick(view: View) {
            addDigit(5)
        }
        fun onButtonSixClick(view: View) {
            addDigit(6)
        }
        fun onButtonSevenClick(view: View) {
            addDigit(7)
        }
        fun onButtonEightClick(view: View) {
            addDigit(8)
        }
        fun onButtonNineClick(view: View) {
            addDigit(9)
        }
    fun onPlusMinusButtonClick(view: View){
        when ((num1*10).toInt()%10){
            0->{
                num1=(num1*(-1))
                result=num1.toInt().toString()
            }
            else->{
                num1=(num1*(-1))
                result=num1.toString()

            }
        }
        binding.mainNumber=result
    }

    fun onButtonPrecentClick(view: View){
        num1=num1/100
        if ((num1*10).toInt()%10==0)
            result=num1.toInt().toString()
        else result=num1.toString()
        previousForDigits=Operation.Precent
        binding.mainNumber=result
    }

    fun onACButtonClick(view: View){
        num1=0.0
        result=num1.toInt().toString()
        binding.mainNumber=result
    }

    fun onPlusButtonClick(view: View){
        num2=num1
        num1=0.0
        previousOperation=Operation.Plus

    }

    fun onMinusButtonClick(view: View){
        num2=num1
        num1=0.0
        previousOperation=Operation.Minus

    }
    fun onMultiplyButtonClick(view: View){
        num2=num1
        num1=0.0
        previousOperation=Operation.Multiply

    }
    fun onDivideButtonClick(view: View){
        num2=num1
        num1=0.0
        previousOperation=Operation.Divide

    }

    fun onEqualsButtonClick(view: View){
        if (previousOperation==Operation.Plus){
            num1=num2+num1
            if ((num1*10).toInt()%10==0)
                result=num1.toInt().toString()
            else result=num1.toString()
            binding.mainNumber=result
        }
        else if(previousOperation==Operation.Minus){
            num1=num2-num1
            if ((num1*10).toInt()%10==0){
                result=num1.toInt().toString()
            }
            else{
                num1=String.format("%.2f", num1).toDouble();
                result=num1.toString()
            }
            binding.mainNumber=result
        }
        else if(previousOperation==Operation.Multiply){
            num1=num2*num1
            if ((num1*10).toInt()%10==0)
                result=num1.toInt().toString()
            else result=num1.toString()
            binding.mainNumber=result
        }
        else if(previousOperation==Operation.Divide){
            when (num1){
                0.0->{
                    binding.mainNumber="Ошибка"
                }
                else->{
                    num1=num2/num1
                    if ((num1*10).toInt()%10==0)
                        result=num1.toInt().toString()
                    else result=num1.toString()
                    binding.mainNumber=result
                }
            }
        }

        previousForDigits=Operation.Equals
        previousOperation=Operation.Equals
    }

    fun onCommaButton(view: View){
        previousForDigits =Operation.Comma
        if(num1 is Double){
            result="${num1.toInt()}."
            binding.mainNumber = result
        }
        else{
            result="$num1."
            binding.mainNumber = result
        }
    }
    private fun addDigit(digit:Int) {
        if (num1.toString().length<9){
            if(previousForDigits==Operation.Comma){
                result="$result"+"$digit"
                num1=result.toDouble()
            }
            else{
                if(previousForDigits== Operation.Equals || previousForDigits==Operation.Precent)
                    num1=0.0
                if (num1 < 0) {
                    num1 *= (-1)
                    num1 = (num1 * 10 + digit) * (-1)
                    result = num1.toInt().toString()
                } else {
                    num1 = num1 * 10 + digit
                    result = num1.toInt().toString()
                }
            }

            previousForDigits=Operation.Digit
            binding.mainNumber = result
        }
        else
            num1=num1

    }

    private fun roundDouble(digit:Double):Double{
        var num= (digit*100).roundToInt()/100
        return num.toDouble()
    }


}

