/*
MIT License

Copyright (c) 2016-2019 Andrés Castellanos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import viper.syntax.Parser;
import viper.tree.Program;


/** Lab main class. */
public final class Main {

  /** Prints help message. */
  private static void help() {
    System.out.println("   __        __           ____  ");
    System.out.println("  / /  ___ _/ /    ____  / __/  ");
    System.out.println(" / /__/ _ `/ _ \\  /___/ / _ \\ ");
    System.out.println("/____/\\_,_/_.__/        \\___/ ");
    System.out.println();
    System.out.println("usage: [options] <files>");
    System.out.println();
    System.out.println("Options:");
    System.out.println("  -h, --help    show this help message and exit");
    System.out.println("  -c, --cycles  runs cycles part");
    System.out.println("  -v, --viper   runs viper part");
    System.exit(0);
  }

  /**
   * Prints a cmd line error.
   *
   * @param msg error message
   */
  private static void error(String msg, boolean help) {
    System.out.println("   __        __           ____  ");
    System.out.println("  / /  ___ _/ /    ____  / __/  ");
    System.out.println(" / /__/ _ `/ _ \\  /___/ / _ \\ ");
    System.out.println("/____/\\_,_/_.__/        \\___/ ");
    System.out.println();
    System.out.println("error: " + msg);
    if (help) {
      System.out.println();
      System.out.println("usage: [options] <files>");
      System.out.println();
      System.out.println("Options:");
      System.out.println("  -h, --help    show this help message and exit");
      System.out.println("  -c, --cycles  runs cycles part");
      System.out.println("  -v, --viper   runs viper part");
    }
    System.exit(0);
  }

  /**
   * Lab main method.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    Options options = new Options();
    options.addOption(Option.builder("h").longOpt("help").build());
    options.addOption(Option.builder("c").longOpt("cycles").build());
    options.addOption(Option.builder("v").longOpt("viper").build());
    try {
      DefaultParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);
      if (cmd.hasOption("help")) {
        help();
      }
      String[] files = cmd.getArgs();
      if (files.length != 0) {
        File file = new File(files[0]);
        if (file.exists()) {
          if (cmd.hasOption("cycles")) {
            try {
              Graph graph = new Graph();
              BufferedReader br = new BufferedReader(new FileReader(file));
              LinkedList<String> classes = new LinkedList<>();
              String fmt = "class %s, or an ancestor of %s, is involved in an inheritance cycle";
              do {
                String line = br.readLine();
                if (line == null) break;
                String[] data = line.split("->");
                String parent = data[0].trim();
                if (!classes.contains(parent)) {
                  classes.add(parent);
                }
                LinkedList<String> children = new LinkedList<>();
                for (String child : data[1].split(",")) {
                  child = child.trim();
                  children.add(child);
                  if (!classes.contains(child)) {
                    classes.add(child);
                  }
                }
                graph.add(parent, children);
              } while (true);
              for (String cls : classes) {
                if (graph.hasCycles(cls)) {
                  System.out.println(String.format(fmt, cls, cls));
                }
              }
            } catch (IOException e) {
              error("I/O errors", false);
            }
          } else if (cmd.hasOption("viper")) {
            Program program = Parser.parse(file);
            program.semant();
            program.dump(System.out);
          }
        } else {
          error("file " + file + " not found", false);
        }
      } else {
        error("no files passed", true);
      }
    } catch (ParseException e) {
      error(e.getMessage().toLowerCase(), true);
    }
  }

}
