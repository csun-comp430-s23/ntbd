package main;

import java.nio.file.Files;

import main.tokenizer.TokenizerException;

import java.io.File;
import java.io.IOException;

public class NTBD {
    public static void usage() {
        System.out.println("Takes:");
        System.out.println("-Input mylang file");
        System.out.println("-Output JavaScript file");
    }

    public static String readFileToString(final String fileName) throws IOException {
        return Files.readString(new File(fileName).toPath());
    }

    public static void main(String[] args)
            throws IOException,
            TokenizerException {
        /*
         * throws IOException,
         * TokenizerException /*,
         * ParseException,
         * CodeGenException
         */ {
            if (args.length != 2) {
                usage();
            } else {
                final String input = readFileToString(args[0]);
                // final Token[] tokens = Tokenizer.tokenize(input);
                // final Program program = Parser.parseProgram(tokens);
                // CodeGen.writeProgram(program, new File(args[1]));
            }
        }
    }
}