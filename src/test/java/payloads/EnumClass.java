package payloads;

public class EnumClass {
	public enum SheetName {
	    GET_ALL("Get All"),
	    POST_REQUEST("postRequest"),
	    UPDATE_REQUEST("Update Request"),
	    DELETE_REQUEST("Delete Request"),
	    ANOTHER_REQUEST("Another Request");

	    private final String sheetName;

	    SheetName(String sheetName) {
	        this.sheetName = sheetName;
	    }

	    public String getSheetName() {
	        return this.sheetName;
	    }
	}

	protected String getSheetName(SheetName sheetName) {
	    return sheetName.getSheetName();
	}


}
