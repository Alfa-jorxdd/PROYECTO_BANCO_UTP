package org.banco.enums;

public enum Formato {
    HTML(".html"),
    PDF(".pdf"),
    EXCEL(".xlsx");
    
    private final String extension;
    
    Formato(String extension){
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
