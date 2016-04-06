CC=javac
FLAGS=-g
SRC=$(wildcard *.java)
TARGETS=$(SRC:.java=.class)
OUT=ImageProcessor205BW
ARGS=img/rock.png 200 .005 

CLASSPATH=.

all: $(TARGETS)
	java -classpath $(CLASSPATH) -ea $(OUT) $(ARGS) #&& xdg-open $(ARGS)

%.class: %.java
	$(CC) -cp $(CLASSPATH) $(FLAGS) $<

run:
	java -classpath $(CLASSPATH) -ea $(OUT) $(ARGS) && xdg-open img/*output.png

debug:$(TARGETS)
	jdb -classpath $(CLASSPATH) $(OUT) $(ARGS)

show:
	xdg-open img/*output.png

clean:
	rm *.class
