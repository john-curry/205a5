CC=javac
FLAGS=-g
SRC=$(wildcard *.java)
TARGETS=$(SRC:.java=.class)
OUT=ImageProcessor205BW
ARGS=img/truck.png#$(firstword $(wildcard img/*.png)) false 0.5 0.1

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
