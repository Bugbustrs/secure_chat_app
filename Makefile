# makkefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)  
# define a variable for the Java Virtual Machine (JVM)
# define a variable for a parameter. When you run make, you could use:
# make run FILE="Algo.csv" para sobre escribir el valor de FILE. 

LIBDIR := lib
SRCDIR := src
BINDIR := bin
JFLAGS =-d ./$(BINDIR)/
JC = javac

JVM= java -cp $(LIBS)
MAIN = Db_assignment

.SUFFIXES: .java .class

default:
	$(JC) $(JFLAGS) $(SRCDIR)/*.java

run_server:
	$(JVM) ./$(BINDIR) Server
	
run_client:
	$(JVM) ./$(BINDIR) ClientGUI

clean:
	$(RM) -f -r *.class 
