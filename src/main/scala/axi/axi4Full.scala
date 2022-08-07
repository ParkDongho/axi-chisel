package axi

import chisel3._
import chisel3.util._
import javax.xml.bind.JAXBIntrospector

/**
  * AXI4 Full Peripheral 
  * @param writeDataWidth is WDATA Width
  * @param writeAddrWidht is WADDR Width
  * @param userReqWidth is WxUSER Width
  *
  */
class AXI4FullIO(
  writeDataWidth: Int, writeAddrWidth: Int, readDataWidth: Int, readAddrWidth: Int, userReqWidth: Int
) extends Bundle {
  val write = new AXI4FullWriteIO(writeDataWidth, writeAddrWidth, userReqWidth)
  val read = new AXI4FullReadIO(readDataWidth, readAddrWidth, userReqWidth)
}

/**
  * AXI4 Write Peripheral
  * @param writeDataWidth is WDATA Width
  * @param writeAddrWidth is AWADDR Width
  * @param userReqWidth is xWUSER Width
  *
  */
class AXI4FullWriteIO(writeDataWidth: Int, writeAddrWidth: Int, userReqWidth: Int) extends Bundle {
  val address  = new DecoupledIO(new AXI4WriteAddressChannel(writeAddrWidth, userReqWidth))
  val data     = new DecoupledIO(new AXI4WriteDataChannel(writeDataWidth, userReqWidth))
  val response = Flipped(new DecoupledIO(new AXI4WriteResponseChannel(userReqWidth)))
}

/**
  * AXI4 Read Peripheral
  * @param readDataWidth is RDATA Widht
  * @param readAddrWidth is RWADDR Width
  * @param userReqWidth is xRUSER Width
  *
  */
 class AXI4FullReadIO(readDataWidth:Int, readAddrWidth: Int, userReqWidth: Int) extends Bundle{
   val address = new DecoupledIO(new AXI4ReadAddressChannel(readAddrWidth, userReqWidth))
   val data    = new DecoupledIO(new AXI4ReadDataChannel(readDataWidth, userReqWidth))
}

/**
  * Address Write Channel of AXI4 Protocol
  * @param writeAddrWidth is AWADDR Width
  * @param userReqWidth is AWUSER Width
  *
  */
class AXI4WriteAddressChannel(writeAddrWidth: Int, userReqWidth: Int) extends Bundle {
  val awid     = UInt(2.W) 
  val awaddr   = UInt(writeAddrWidth.W)
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

/**
  * Write Data Channel of AXI4 Protocol
  * @param writeDataWidth is WDATA Width
  * @param userReqWidth is WUSER Width
  *
  */
class AXI4WriteDataChannel(writeDataWidth: Int, userReqWidth: Int) extends Bundle{
  val wid   = UInt(2.W)
  val wdata = UInt(writeDataWidth.W)
  val wstrb = UInt((writeDataWidth/8).W)
  val wlast = Bool()
  val wuser = UInt(userReqWidth.W)
}

/**
  * Write Response Channel of AXI4 Protocol
  * @param userReqWidth is BUSER Width
  *
  */
class AXI4WriteResponseChannel(userReqWidth: Int) extends Bundle{
  val bid   = UInt(2.W)
  val bresp = UInt(2.W)
  val buser = UInt(userReqWidth.W)
}

/**
  * Read Address Channel of AXI4 Protocol
  * @param readAddrWidth is ARADDR Width
  * @param userReqWidth is ARUSER Width
  *
  */
class AXI4ReadAddressChannel(readAddrWidth: Int, userReqWidth: Int) extends Bundle{
  val arid     = UInt(2.W)
  val araadr   = UInt(readAddrWidth.W)
  val arlen    = UInt(8.W)
  val arsize   = UInt(2.W)
  val arburst  = UInt(2.W)
  val arlock   = UInt(2.W)
  val arcache  = UInt(3.W)
  val arprot   = UInt(3.W)
  val arregion = UInt(4.W)
  val aruser   = UInt(userReqWidth.W)
}

/**
  * Read Data Channel of AXI4 Protocol
  *
  * @param readDataWidth is RDATA Width
  * @param userReqWidth is RUSER Width
  *
  */
class AXI4ReadDataChannel(readDataWidth: Int, userReqWidth: Int) extends Bundle {
  val rid   = UInt(2.W)
  val rdata = UInt(readDataWidth.W)
  val rstrb = UInt((readDataWidth/8).W)
  val rlast = Bool()
  val ruser = UInt(userReqWidth.W)
}
