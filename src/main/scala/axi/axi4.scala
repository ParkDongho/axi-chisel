package axi

import chisel3._
import chisel3.util._

class AxiIO[T <: Data](writeData: T, writeAddr:T, userReqWidth: Int) extends Bundle {
  val write = new AxiWriteIO(writeData, writeAddr, userReqWidth)
  val read = new AxiReadIO()
}

class AxiWriteIO[T <: Data](writeData: T, writeAddr: T, userReqWidth: Int) extends Bundle {
  val address = new DecoupledIO(new AxiWriteAddressChannel(writeAddr, userReqWidth))
  val data = new DecoupledIO(new AxiWriteDataChannel(writeData, userReqWidth))
  val response = Flipped(new DecoupledIO(new AxiWriteResponseChannel))
}

class AxiWriteAddressChannel[T <: Data](writeAddr: T, userReqWidth: Int) extends Bundle {
  val awid     = UInt(2.W)
  val awaddr   = writeAddr
  val awlen    = UInt(8.W)
  val awsize   = UInt(2.W)
  val awburst  = UInt(2.W)
  val awlock   = UInt(2.W)
  val awcache  = UInt(3.W)
  val awprot   = UInt(3.W)
  val awqos    = UInt(4.W)
  val awregion = UInt(4.W)
  val awuser   = UInt(userReqWidth.W)
}

class AxiWriteDataChannel[T <: Data](writeData: T, userReqWidth: Int) extends Bundle{
  val wid   = UInt(2.W)
  val wdata = writeData
  val wstrb = UInt((writeData.getWidth/8).W)
  val wlast = Bool()
  val wuser = UInt(userReqWidth.W)
}

class AxiWriteResponseChannel extends Bundle{



}

class AxiReadIO[T <:Data]() extends Bundle{


}
