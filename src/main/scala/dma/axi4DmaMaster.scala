package axi

import chisel3._
import chisel3.util._
import axi._

/**
  * 
  * @param writeDataWidth is 
  * @param writeAddrWidth
  * @param userReqWidth
  */
class DMAFull2Stream(writeDataWidth: Int, writeAddrWidth:Int, userReqWidth: Int) extends Bundle {
  val io = new Bundle {
    val memoryIn = new AXI4FullWriteIO(writeDataWidth, writeAddrWidth, userReqWidth)
    val streamOut = new AXI4StreamIO()
    val control = Flipped(new AXI4StreamIO())
  }
}
