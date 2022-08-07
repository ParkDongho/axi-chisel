package axi

import chisel3._
import chisel3.util._

abstract class AxiDma[T <: Data](writeData: T, writeAddr:T, userReqWidth: Int) extends Bundle {
  val io = new AxiIO(writeData, writeAddr, userReqWidth)

}
