WABP01:=wabp01:/usr/local/apache-tomcat-6.0.35/webapps/
EXCLUDE:=exclude.txt


default:
	@echo "sync to wabp01:\n\tmake wabp01" 
	@echo "sync to all:\n\tmake all" 

wabp01:
	rsync -av ../wns ${WABP01} --exclude-from=${EXCLUDE}

all: wabp01
