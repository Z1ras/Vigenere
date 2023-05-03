all: run

clean:
	rm -f src/*.class out/Fibon.jar out/Fib.jar

out/Fibon.jar: out/parcs.jar src/Fibon.java src/Data.java
	@javac -cp out/parcs.jar src/Fibon.java src/Data.java
	@jar cf out/Fibon.jar -C src Fibon.class -C src Data.class
	@rm -f src/Fibon.class src/Data.class

out/Fib.jar: out/parcs.jar src/Fib.java src/Data.java
	@javac -cp out/parcs.jar src/Fib.java src/Data.java
	@jar cf out/Fib.jar -C src Fib.class -C src Data.class
	@rm -f src/Fib.class src/Data.class

build: out/Fibom.jar out/Fib.jar

run: build
	@cd out && java -cp 'parcs.jar:Fibon.jar:Fib.jar' Fibon