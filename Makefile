JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src/main
BINDIR=bin
LIBDIR=lib

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR):$(LIBDIR)/forms_rt.jar $<

CLASSES=Post.class Account.class BST.class MainFrame.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/main/%.class)

default: $(CLASS_FILES)
clean:
	rm $(BINDIR)/*.class
run: $(CLASS_FILES)
	java -cp $(BINDIR):$(LIBDIR)/forms_rt.jar main/MainFrame
javadoc:
	javadoc -d docs -cp bin:lib/forms_rt.jar -sourcepath src/ main