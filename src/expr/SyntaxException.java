

package expr;


public class SyntaxException extends Exception {

    public static final int INCOMPLETE = 0;
    public static final int BAD_FACTOR = 1;

   
    public static final int PREMATURE_EOF = 2;

   
    public static final int EXPECTED = 3;

   
    public static final int UNKNOWN_VARIABLE = 4;

  
    public SyntaxException(String complaint, 
			   Parser parser,
			   int reason, 
			   String expected) {
	super(complaint); 
	this.reason = reason;
	this.parser = parser;
	this.scanner = parser.tokens;
	this.expected = expected;
    }

    
    public String explain() {
	StringBuffer sb = new StringBuffer();

	sb.append("I don't understand your formula ");
	quotify(sb, scanner.getInput());
	sb.append(".\n\n");

	explainWhere(sb);
	explainWhy(sb);
	explainWhat(sb);

	return sb.toString();
    }

    private Parser parser;
    private Scanner scanner;

    private int reason;
    private String expected;

    private String fixedInput = "";

    private void explainWhere(StringBuffer sb) {
	if (scanner.isEmpty()) {
	    sb.append("It's empty!\n");
	} else if (scanner.atStart()) {
	    sb.append("It starts with ");
	    quotify(sb, theToken());
	    if (isLegalToken()) 
		sb.append(", which can never be the start of a formula.\n");
	    else
		sb.append(", which is a meaningless symbol to me.\n");
	} else {
	    sb.append("I got as far as ");
	    quotify(sb, asFarAs());
	    sb.append(" and then ");
	    if (scanner.atEnd()) {
		sb.append("reached the end unexpectedly.\n");
	    } else {
		sb.append("saw ");
		quotify(sb, theToken());
		if (isLegalToken()) 
		    sb.append(".\n");
		else
		    sb.append(", which is a meaningless symbol to me.\n");
	    }
	}
    }

    private void explainWhy(StringBuffer sb) {
	switch (reason) {
	case INCOMPLETE: 
	    if (isLegalToken())
		sb.append("The first part makes sense, but I don't see " +
			  "how the rest connects to it.\n");
	    break;
	case BAD_FACTOR:
	case PREMATURE_EOF:
	    sb.append("I expected a value");
	    if (!scanner.atStart()) sb.append(" to follow");
	    sb.append(", instead.\n");
	    break;
	case EXPECTED:
	    sb.append("I expected ");
	    quotify(sb, expected);
	    sb.append(" at that point, instead.\n");
	    break;
	case UNKNOWN_VARIABLE:
	    sb.append("That variable has no value.\n");
	    break;
	default:
	    throw new Error("Can't happen");
	}
    }

    private void explainWhat(StringBuffer sb) {
	fixedInput = tryToFix();
	if (null != fixedInput) {
	    sb.append("An example of a formula I can parse is ");
	    quotify(sb, fixedInput);
	    sb.append(".\n");
	}
    }

    private String tryToFix() {
	return (parser.tryCorrections() ? scanner.toString() : null);
    }

    private void quotify(StringBuffer sb, String s) {
	sb.append('"');
	sb.append(s);
	sb.append('"');
    }

    private String asFarAs() {
	Token t = scanner.getCurrentToken();
	int point = t.location - t.leadingWhitespace;
	return scanner.getInput().substring(0, point);
    }

    private String theToken() {
	return scanner.getCurrentToken().sval;
    }

    private boolean isLegalToken() {
	Token t = scanner.getCurrentToken();
	return t.ttype != Token.TT_EOF
	    && t.ttype != Token.TT_ERROR;
    }
}
