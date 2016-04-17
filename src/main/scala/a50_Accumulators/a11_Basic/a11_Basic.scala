package a50_Accumulators.a11_Basic

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext._
import org.apache.spark.SparkContext
object a020_Accumulator {

  def main(args: Array[String]) {
    println("Inside driver")
    
    //val zeroVal = args(0).toInt
    //println("zeroVal -> " + zeroVal)
    
    val conf = new SparkConf().setAppName("a020_Accumulator")
    val sc = new SparkContext(conf)

    val input = sc.parallelize(List(1,  2,  3,  4,  5,
                                    6,  7,  8,  9,  10,
                                    11, 12, 13, 14, 15
                                    ))
                                      .repartition(3)
    println("No of partitions -> " + input.partitions.size)
    
    val myAccum = sc.accumulator(0, "My Accumulator")
    
    // Used inside an action
    input.foreach{ x =>   
      Thread.sleep(50000)
        myAccum += x.toInt
    }
    println("myAccum -> " + myAccum.value)
    
    sc.stop()
  }                                       
}