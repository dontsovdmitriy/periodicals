package controller.tag;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DecimalFormat;

public class CostFormatTag extends TagSupport {

    private static final Logger logger = Logger.getLogger(CostFormatTag.class);

    private int cost;

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int doStartTag() throws JspException {
    	//TODO посмотреть если убрать #
		DecimalFormat f = new DecimalFormat("#,##0.00;-#,##0.00");
		 f.format(cost/100.0);
		 String formattedCost = f.format(cost/100.0);
		 
        try {
            pageContext.getOut().write(formattedCost);
        } catch (IOException e) {
            logger.error("Exception during the formatted price: ", e);
            throw new JspException(e);
        }
        
        return SKIP_BODY;
    }
}
