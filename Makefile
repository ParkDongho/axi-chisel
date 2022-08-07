SBT = sbt
SOURCE = source
OPEN = open

CURR_DIR:=$(shell pwd)

SSH_FILE := ~/dev/chisel/setup/ssh.txt 
PORT_FILE := ~/dev/chisel/setup/port.txt
REMOTE_SSH := $(shell cat ${SSH_FILE})
REMOTE_PORT := $(shell cat ${PORT_FILE})

# 서버상의 Vivado 프로젝트의 verilog 파일이 저장되는 디렉토리를 설정
REMOTE_PROJ_DIR := ~/Documents/dev/NPUgen-chisel-project/NPUgen-chisel-project.srcs/sources_1/new


#######################
# Generate Verilog Code
Add:
	$(SBT) "runMain empty.AddMain" \ #chisel -> verilog 컴파일
		&& scp -P ${REMOTE_PORT} ./generated/Add.v \   #Add.v라는 이름의
		${REMOTE_SSH}:${REMOTE_PROJ_DIR}               #베릴로그 파일을 서버에 전송

##############
# Run the test
Add_Test:
	$(SBT) "testOnly AddTest"

##################################
# Draw Timing Diagram with GTKWave
Add_Wave:
	$(OPEN) ./test_run_dir/Add_should_pass/*.vcd

####################
# Draw Block Diagram
Add_Diagrammer:
	$(SOURCE) ./util/draw_diagrammer.sh -f "Add"

clean:
	rm -rf generated
	rm -rf project
	rm -rf target

help:
	echo "사용방법"
	echo ""
