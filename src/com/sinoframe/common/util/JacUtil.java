package com.sinoframe.common.util;

import com.jacob.activeX.*;
import com.jacob.com.*;

public class JacUtil extends java.awt.Panel {
	private ActiveXComponent MsWordApp = null;
	private Dispatch document = null;

	public JacUtil() {
		super();
	}

	public void openWord(boolean makeVisible)throws Exception {
		// Open Word if we've not done it already
		if (MsWordApp == null) {
			MsWordApp = new ActiveXComponent("Word.Application");
		}
		// Set the visible property as required.
		Dispatch.put(MsWordApp, "Visible", new Variant(makeVisible));
	}

	public void createNewDocument() {
		// Find the Documents collection object maintained by Word
		Dispatch documents = Dispatch.get(MsWordApp, "Documents").toDispatch();
		// Call the Add method of the Documents collection to create
		// a new document to edit
		document = Dispatch.call(documents, "Add").toDispatch();
	}

	public void insertText(String textToInsert) {
		// Get the current selection within Word at the moment. If
		// a new document has just been created then this will be at
		// the top of the new doc
		Dispatch selection = Dispatch.get(MsWordApp, "Selection").toDispatch();
		// Put the specified text at the insertion point
		Dispatch.put(selection, "Text", textToInsert);
	}

	public void saveFileAs(String filename) {
		Dispatch.call(document, "SaveAs", filename);
	}

	public void printFile() {
		// Just print the current document to the default printer
		Dispatch.call(document, "PrintOut");
	}

	public void closeDocument() {
		// Close the document without saving changes
		// 0 = wdDoNotSaveChanges
		// -1 = wdSaveChanges
		// -2 = wdPromptToSaveChanges
		Dispatch.call(document, "Close", new Variant(0));
		document = null;
	}

	public void closeWord() {
		Dispatch.call(MsWordApp, "Quit");
		MsWordApp = null;
		document = null;
	}

	public static void main(String[] args) throws Exception {
		JacUtil word = new JacUtil();
		word.openWord(false);
		
		word.createNewDocument();
		word.insertText("Hello word.");
		word.saveFileAs("D:\\aa.doc");
	}
}



