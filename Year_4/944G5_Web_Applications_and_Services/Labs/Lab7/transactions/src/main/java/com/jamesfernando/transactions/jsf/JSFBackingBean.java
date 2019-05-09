package com.jamesfernando.transactions.jsf;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import com.jamesfernando.transactions.ejb.ExecutorEJB;
import com.jamesfernando.transactions.operations.Operation;
import com.jamesfernando.transactions.operations.ParseException;

@Named(value = "exerciseBean")
@ConversationScoped
public class JSFBackingBean implements Serializable {

    @EJB
    ExecutorEJB transactionTester;
    private List<Operation> ops;

    private String operationString;
    private String results;
    private String parseOutput;

    public JSFBackingBean() {

    }

    public String getOperationString() {
        return operationString;
    }

    public void setOperationString(String operationString) {
        this.operationString = operationString;
        try {
            this.ops = Parser.parse(this.operationString);
            parseOutput = ops.toString();
        } catch (ParseException pe) {
            parseOutput = pe.getMessage();
        }
    }

    public String getParseOutput() {
        return parseOutput;
    }

    public void setParseOutput(String parseOutput) {
        this.parseOutput = parseOutput;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String execute() {
        results = transactionTester.execute(ops);
        setParseOutput(ops.toString());
        return "index";
    }
}
