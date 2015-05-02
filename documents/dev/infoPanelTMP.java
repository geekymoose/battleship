
    /*
     * @deprecated Not used at the moment
     * @param string 
     */
    public void printInfoMessage(String string) {
        ta_info.append(string);
        ta_info.setCaretPosition(ta_info.getText().length());
    }
    
    /*
     * @deprecated Not used at the moment
     */
    private void setupTextArea() {
        ta_info     = new JTextArea();
        ta_info     .setLineWrap(true);
        ta_info     .setEditable(false);
        sp_scroll   = new JScrollPane(ta_info);
        sp_scroll   .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
    