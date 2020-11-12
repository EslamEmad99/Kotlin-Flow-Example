package eslam.emad.kotlinflowsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  LOG_TEST = "log_test"

        val flow = flow {
            repeat(10) {
                emit("$it")
                delay(1000)
            }
        }

       val job = lifecycleScope.launch {
            flow.buffer()
                    .map {
                        it.toInt()
                    }
                    .filter {
                        it % 2 == 0
                    }
                    .map{
                        it*it
                    }
                    .collect {
                        println("$LOG_TEST $it")
                        delay(2000)
                    }
        }

        //job.cancel()
    }
}