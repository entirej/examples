package org.miap.forms.constants;

/* AUTO-GENERATED FILE.  DO NOT MODIFY. 
 *
 * This class was automatically generated by the
 * entirej plugin from the form.  It
 * should not be modified by hand.
 */
public class F_MIAP002
{
    public static final String ID = "MIAP002";

    public static class B_INVOICE_TOOLBAR
    {
        public static final String ID       = "InvoiceToolbar";
        public static final String I_CLOSE  = "close";
        public static final String I_NEW    = "new";
        public static final String I_EDIT   = "edit";
        public static final String I_DELETE = "delete";

    }

    public static class B_INVOICE_BLOCK
    {
        public static final String ID                = "InvoiceBlock";
        public static final String I_PAID            = "paid";
        public static final String I_CUST_ID         = "custId";
        public static final String I_PRINTED         = "printed";
        public static final String I_NR              = "nr";
        public static final String I_INV_ID          = "invId";
        public static final String I_AMOUNT_INCL_VAT = "amountInclVat";
        public static final String I_COINF_ID        = "coinfId";
        public static final String I_INV_DATE        = "invDate";
        public static final String I_ID              = "id";
        public static final String I_PAYINF_ID       = "payinfId";
        public static final String I_AMOUNT_EXCL_VAT = "amountExclVat";
        public static final String I_CUSTOMER_NAME   = "customerName";
        public static final String I_COMPANY_NAME    = "companyName";
        public static final String I_PAYMENT_TERMS   = "paymentTerms";

    }

    public static class B_INVOICE_POSITIONS_TOOLBAR
    {
        public static final String ID       = "InvoicePositionsToolbar";
        public static final String I_CLOSE  = "close";
        public static final String I_NEW    = "new";
        public static final String I_EDIT   = "edit";
        public static final String I_DELETE = "delete";

    }

    public static class B_INVOICE_POSITIONS_BLOCK
    {
        public static final String ID                   = "InvoicePositionsBlock";
        public static final String I_HOURS_WORKED       = "hoursWorked";
        public static final String I_CUPR_ID            = "cuprId";
        public static final String I_ID                 = "id";
        public static final String I_POSITION           = "position";
        public static final String I_VAT_ID             = "vatId";
        public static final String I_INV_ID             = "invId";
        public static final String I_DESCRIPTION        = "description";
        public static final String I_AMOUNT             = "amount";
        public static final String I_VAT_RATE           = "vatRate";
        public static final String I_CUST_PROJ_NAME     = "custProjName";
        public static final String I_CUST_PROJ_PAY_RATE = "custProjPayRate";

    }

    public static class B_INVOICE_TOTALS_BLOCK
    {
        public static final String ID                  = "InvoiceTotalsBlock";
        public static final String I_TOTAL_AMT_INC_VAT = "TotalAmtIncVAT";

    }

    public static class L_CUSTOMER
    {
        public static final String ID               = "Customer";
        public static final String I_ID             = "id";
        public static final String I_ADDRESS_LINE_1 = "addressLine1";
        public static final String I_ADDRESS_LINE_2 = "addressLine2";
        public static final String I_NAME           = "name";
        public static final String I_POST_CODE      = "postCode";
        public static final String I_ADDRESS_LINE_3 = "addressLine3";
        public static final String I_TOWN           = "town";

    }

    public static class L_COMPANY_INFORMATION
    {
        public static final String ID                    = "CompanyInformation";
        public static final String I_NAME                = "name";
        public static final String I_BANK_TOWN           = "bankTown";
        public static final String I_IBAN                = "iban";
        public static final String I_TOWN                = "town";
        public static final String I_ADDRESS_LINE_1      = "addressLine1";
        public static final String I_BANK_ADDRESS_LINE_3 = "bankAddressLine3";
        public static final String I_BANK_POST_CODE      = "bankPostCode";
        public static final String I_ADDRESS_LINE_3      = "addressLine3";
        public static final String I_BANK_ADDRESS_LINE_1 = "bankAddressLine1";
        public static final String I_ADDRESS_LINE_2      = "addressLine2";
        public static final String I_ID                  = "id";
        public static final String I_BANK_ADDRESS_LINE_2 = "bankAddressLine2";
        public static final String I_BANK_NAME           = "bankName";
        public static final String I_POST_CODE           = "postCode";

    }

    public static class L_PAYMENT_INFORMATION
    {
        public static final String ID              = "PaymentInformation";
        public static final String I_ID            = "id";
        public static final String I_PAYMENT_TERMS = "paymentTerms";

    }

    public static class L_VAT_RATES
    {
        public static final String ID      = "VatRates";
        public static final String I_NOTES = "notes";
        public static final String I_RATE  = "rate";
        public static final String I_NAME  = "name";
        public static final String I_ID    = "id";

    }

    public static class L_CUSTOMER_PROJECTS
    {
        public static final String ID            = "CustomerProjects";
        public static final String I_NAME        = "name";
        public static final String I_ID          = "id";
        public static final String I_PAY_RATE    = "payRate";
        public static final String I_CUST_ID     = "custId";
        public static final String I_DESCRIPTION = "description";

    }

    public static final String C_INVOICES_GROUP            = "InvoicesGroup";
    public static final String C_INVOICE_TOOLBAR           = "InvoiceToolbar";
    public static final String C_INVOICE_BLOCK             = "InvoiceBlock";
    public static final String C_INVOICE_TOTALS_BLOCK      = "InvoiceTotalsBlock";
    public static final String C_INVOICE_POSITIONS_TOOLBAR = "InvoicePositionsToolbar";
    public static final String C_INVOICE_POSITIONS         = "InvoicePositions";

    public static final String AC_TOOLBAR_DELETE           = "TOOLBAR_DELETE";
    public static final String AC_TOOLBAR_EDIT             = "TOOLBAR_EDIT";
    public static final String AC_TOOLBAR_NEW              = "TOOLBAR_NEW";

}