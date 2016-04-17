package a70_Broadcast_Variable.a11_Basic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object a025_Broadcast_Variable {
  
  def main(args: Array[String]) {
    println("Inside driver")
    
    //val zeroVal = args(0).toInt
    //println("zeroVal -> " + zeroVal)
    
    val conf = new SparkConf().setAppName("a025_Broadcast_Variable")
    val sc = new SparkContext(conf)
    val broadcastVar = sc.broadcast(1)
    
    val input = sc.parallelize(List(1,  2,  3))
      
    val res = input.map(x => broadcastVar.value + x)
    
    println("Result -> " + res.collect().foreach(println))
  }
}

/*
// Yarn
~/sk/bin/spark-submit --class a.a025_Broadcast_Variable.a025_Broadcast_Variable --master yarn --deploy-mode client target/scala-2.10/sparkexamples_2.10-1.0.jar
2
3
4
Result -> ()

//Local Mode
~/sk/bin/spark-submit --class a.a025_Broadcast_Variable.a025_Broadcast_Variable --deploy-mode client target/scala-2.10/sparkexamples_2.10-1.0.jar
Inside driver
2016-04-06 08:45:27.014 java[10874:3504955] Unable to load realm info from SCDynamicStore
16/04/06 08:45:27 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
2
3
4
Result -> ()


*/