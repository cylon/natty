package com.joestelmach.natty;

import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Joe Stelmach
 */
public class DateGroup {
  private List<Date> _dates;
  private String _text;
  private String _fullText;
  private int _line;
  private int _position;
  private boolean _isRecurring;
  private boolean _isDateInferred;
  private boolean _isTimeInferred;
  private Date _recurringUntil;
  private Map<String, List<ParseLocation>> _parseLocations;
  private Tree _syntaxTree;

  public DateGroup() {
    _dates = new ArrayList<Date>();
    _isDateInferred = true;
    _isTimeInferred = true;
  }

  public String getFullText() {
    return _fullText;
  }

  public void setFullText(String fullText) {
    this._fullText = fullText;
  }

  public boolean isDateInferred() {
    return _isDateInferred;
  }

  public void setDateInferred(boolean isDateInferred) {
    this._isDateInferred = isDateInferred;
  }

  public List<Date> getDates() {
    return _dates;
  }
  public void addDate(Date date) {
    _dates.add(date);
  }
  
  public String getText() {
    return _text;
  }
  public void setText(String text) {
    _text = text;
  }

  public int getLine() {
    return _line;
  }
  public void setLine(int line) {
    _line = line;
  }

  public int getPosition() {
    return _position;
  }
  public void setPosition(int position) {
    _position = position;
  }
  
  public boolean isRecurring() {
    return _isRecurring;
  }
  public void setRecurring(boolean isRecurring) {
    _isRecurring = isRecurring;
  }
  
  /**
   * @return true if the time information in this date group has been inferred
   * as opposed to being explicity defined in the input.
   */
  public boolean isTimeInferred() {
    return _isTimeInferred;
  }
  public void setIsTimeInferred(boolean isTimeInferred) {
    this._isTimeInferred = isTimeInferred;
  }
  
  public Date getRecursUntil() {
    return _recurringUntil;
  }
  public void setRecurringUntil(Date recurringUntil) {
    _recurringUntil = recurringUntil;
  }

  public Map<String, List<ParseLocation>> getParseLocations() {
    return _parseLocations;
  }
  public void setParseLocations(Map<String, List<ParseLocation>> parseLocations) {
    _parseLocations = parseLocations;
  }

  public Tree getSyntaxTree() {
    return _syntaxTree;
  }

  public void setSyntaxTree(Tree syntaxTree) {
    _syntaxTree = syntaxTree;
  }

  public String getPrefix(int length) {
    return _fullText.substring(Math.max(0, getAbsolutePosition() - length), getAbsolutePosition());
  }

  public String getSuffix(int length) {
    int endPosition = getAbsolutePosition() + _text.length() + 1;
    return _fullText.substring(
        Math.min(endPosition - 1, _fullText.length()),
        Math.min(endPosition + length - 1, _fullText.length()));
  }

  public int getAbsolutePosition() {
    int lineCount = 1;
    int columnCount = 1;
    for(int i=0; i<_fullText.length(); i++) {

      // return current position if we've arrived at the requested character
      if(lineCount == _line && columnCount == _position) {
        return i;
      }

      if(_fullText.charAt(i) == '\n') {
        lineCount++;
        columnCount = 1;
      }
      else {
        columnCount++;
      }
    }
    return -1;
  }

}
