package com.jamesfernando.transactions.jsf;

import com.jamesfernando.transactions.operations.Delay;
import com.jamesfernando.transactions.operations.Operation;
import com.jamesfernando.transactions.operations.ParseException;
import com.jamesfernando.transactions.operations.Read;
import com.jamesfernando.transactions.operations.Write;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianw
 */
public class Parser {

    public static List<Operation> parse(String input) throws ParseException {
        List<Operation> ops = new ArrayList<Operation>();
        try {
            StreamTokenizer scanner = new StreamTokenizer(new StringReader(input));
            while (scanner.nextToken() != StreamTokenizer.TT_EOF) {
                String t = scanner.sval;
                if (t.toLowerCase().startsWith("r")) {
                    ops.add(parseRead(scanner));
                } else if (t.toLowerCase().startsWith("w")) {
                    ops.add(parseWrite(scanner));
                } else if (t.toLowerCase().startsWith("d")) {
                    ops.add(parseDelay(scanner));
                } else {
                    throw new ParseException("Unknown Operation: " + t);
                }
            }
        } catch (IOException ioe) {
            throw new ParseException("Scanner generated " + ioe.getMessage());
        }

        return ops;
    }

    private static Operation parseRead(StreamTokenizer scanner) throws ParseException, IOException {
        if (scanner.nextToken() == '(') {
            if (scanner.nextToken() == StreamTokenizer.TT_WORD) {
                String id = scanner.sval;
                if ((id.compareTo("x") != 0) && (id.compareTo("y") != 0)) {
                    throw new ParseException("Unrecognised record id: " + id);
                }
                if (scanner.nextToken() == ')') {
                    if (scanner.nextToken() == ';') {
                        return new Read(id);
                    } else {
                        throw new ParseException("Expected ';'");
                    }
                } else {
                    throw new ParseException("Expected ')'");
                }
            } else {
                throw new ParseException("Expected record id whilst parsing read");
            }
        } else {
            throw new ParseException("Expected '('");
        }
    }

    private static Operation parseWrite(StreamTokenizer scanner) throws IOException, ParseException {
        if (scanner.nextToken() == '(') {
            if (scanner.nextToken() == StreamTokenizer.TT_WORD) {
                String id = scanner.sval;
                if ((id.compareTo("x") != 0) && (id.compareTo("y") != 0)) {
                    throw new ParseException("Unrecognised record id: " + id);
                }
                if (scanner.nextToken() == ',') {
                    if (scanner.nextToken() == StreamTokenizer.TT_NUMBER) {
                        int value = (int) scanner.nval;
                        if (scanner.nextToken() == ')') {
                            if (scanner.nextToken() == ';') {
                                return new Write(id, value);
                            } else {
                                throw new ParseException("Expected ';'");
                            }
                        } else {
                            throw new ParseException("Expected ')'");
                        }
                    } else {
                        throw new ParseException("Expected number in parsing write");
                    }
                } else {
                    throw new ParseException("Expected comma in parsing write");
                }

            } else {
                throw new ParseException("Expected record id in parsing write");
            }
        } else {
            throw new ParseException("Expected '('");
        }
    }

    private static Operation parseDelay(StreamTokenizer scanner) throws IOException, ParseException {
        if (scanner.nextToken() == '(') {
            if (scanner.nextToken() == StreamTokenizer.TT_NUMBER) {
                int value = (int) scanner.nval;
                if (scanner.nextToken() == ')') {
                    if (scanner.nextToken() == ';') {
                        return new Delay(value);
                    } else {
                        throw new ParseException("Expected ';'");
                    }
                } else {
                    throw new ParseException("Expected ')'");
                }
            } else {
                throw new ParseException("Expected number of seconds whilst parsing delay");
            }
        } else {
            throw new ParseException("Expected '('");
        }
    }
}
