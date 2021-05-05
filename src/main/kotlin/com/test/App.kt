package com.test

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.LambdaLogger

class App : RequestHandler<HandlerInput, HandlerOutput> {

    override fun handleRequest(input: HandlerInput?, context: Context?): HandlerOutput {
        var log: LambdaLogger = context!!.logger
        input?.let {
            var method1 = it.method.toString() //optional as string

            fun getMethodtype(method: String): MethodType {
                return try {
                    log.log("getmethod values are ${MethodType.valueOf(method.toUpperCase())} ")
                    MethodType.valueOf(method.toUpperCase())
                } catch (ex: Exception) {
                    log.log("methos val ${ex.printStackTrace()}")
                    MethodType.MULTIPLY
                }

            }

            fun calculate(arugument1: Double, argument2: Double, method: String): Any {
                log.log("calculate values are ${method} ")
                return when (getMethodtype(method)) {
                    MethodType.ADD -> arugument1 + argument2
                    MethodType.SUB -> arugument1 - argument2
                    MethodType.DIVIDE -> arugument1 / argument2
                    MethodType.MULTIPLY -> arugument1 * argument2
                    MethodType.NONE -> 0

                }
            }

            fun getDataType(datatype: String): DataType {
                return try {
                    log.log("datatype values are ${datatype} ")
                    DataType.valueOf(datatype.toUpperCase())
                } catch (ex: Exception) {
                    log.log("${ex.printStackTrace()}")
                    /***used here as kotlin intelisence error******/
                    DataType.BYTE
                }
            }


            fun operation(arugument1: Double, argument2: Double, method: String, datatype: String): Any {
                log.log("operation values are ${datatype} ")
                return when (getDataType(datatype)) {
                    DataType.INT -> calculate(arugument1, argument2, method)
                    DataType.BYTE -> calculate(arugument1, argument2, method)
                    DataType.FLOAT -> calculate(arugument1, argument2, method)
                    DataType.DOUBLE -> calculate(arugument1, argument2, method)
                    DataType.LONG -> calculate(arugument1, argument2, method)
                    DataType.SHORT -> calculate(arugument1, argument2, method)
                    DataType.NONE -> 0
                }

            }

            if (it.argument1 == "" || it.argument2 == "" || it.method.equals("") || it.datatype == "") {
                return HandlerOutput(
                    it.argument1, it.argument2,
                    "", it.datatype, method1, "One or more of input fields was null", "422"
                )
            }
            if ( it.argument2.equals("0")) {
                return HandlerOutput(
                    it.argument1, it.argument2,
                    "", it.datatype, method1, "Division by zero error", "422"
                )
            }

            if (getMethodtype(method1).equals(MethodType.NONE)) {

                return HandlerOutput(
                    it.argument1, it.argument2,
                    "", it.datatype, method1, "Unsupported operation", "422"
                )
            }
            if (getDataType(it.datatype).equals(DataType.NONE)) {

                return HandlerOutput(
                    it.argument1,
                    it.argument2,
                    "",
                    it.datatype,
                    method1,
                    "At least one of argument1, argument2 could not be resolved with declared dataType",
                    "422"
                )
            }
            log.log(
                "input ${it.argument1.toDouble()} and ${method1} and ${it.datatype} values are ${
                    operation(
                        it.argument1.toDouble(),
                        it.argument2.toDouble(),
                        method1,
                        it.datatype
                    )
                } and ${it.datatype}"
            )

            return HandlerOutput(
                it.argument1,
                it.argument2,
                operation(it.argument1.toDouble(), it.argument2.toDouble(), method1, it.datatype.toString()).toString(),
                it.datatype,
                method1,
                "Success",
                "200"
            )
        }


        return HandlerOutput("", "", 0, "", "", "failure", "403");
    }
}


