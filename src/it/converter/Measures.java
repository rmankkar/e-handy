package it.converter;

import java.util.Locale;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import eHandy.gtbit.R;
import expr.Expr;
import expr.Parser;
import expr.SyntaxException;
import expr.Variable;


public class Measures implements Parcelable {
	
	private String id, id_eng = "";
	private String symbol;
	private boolean base, personal = false, visibile = true;
	private String to, from;
	private Expr to_expr, from_expr;
	private String unit_ref = "m";
	
	public String getId() {
		
		
			return id_eng;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId_eng() {
		return id_eng;
	}

	public void setId_eng(String id_eng) {
		this.id_eng = id_eng;
	}
	
	public boolean isBase() {
		return base;
	}
	
	public void setBase(String base) {
		this.base = base.contentEquals("true");
	}
	
	public void setBase(Boolean base) {
		this.base = base;
	}

	public boolean isPersonal() {
		return personal;
	}

	public void setPersonal(boolean personale) {
		this.personal = personale;
	}
	
	public void setPersonal(String personale) {
		this.personal = personale.contentEquals("true");
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean show) {
		this.visibile = show;
	}
	
	public void setVisibile(String show) {
		this.visibile = show.contentEquals("true");
	}
	
	public Double getTo(double value, String xx) {		
		Variable x = Variable.make(xx);
		x.setValue(value);
		return to_expr.value();
	}
	
	public Double getTo(double value) {
		return getTo(value, "x");
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String conversion) {
		try {
			this.to = conversion;
			to_expr = Parser.parse(this.to);     
		} catch (SyntaxException e) {
			Log.e("Expr", e.explain());
		}
	}	
	
	public Double getFrom(double value, String xx) {		
		Variable x = Variable.make(xx);
		x.setValue(value);
		return from_expr.value();
	}
	
	public Double getFrom(double value) {
		return getFrom(value, "x");
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String conversion) {
		try {
			this.from = conversion;
			from_expr = Parser.parse(this.from); 
		} catch (SyntaxException e) {
		    Log.e("Expr", e.explain());
		}
	}	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(id_eng);
		dest.writeString(symbol);
		dest.writeString(unit_ref);
		dest.writeString(to);
		dest.writeString(from);
		dest.writeInt(bToint(base));
		dest.writeInt(bToint(personal));
		dest.writeInt(bToint(visibile));
	}
	
	private int bToint(boolean input) {
		if (input)
			return 1;
		else
			return 0;
	}
	
	public Measures(Parcel in) {
        id = in.readString();
        id_eng = in.readString();
        symbol = in.readString();
        unit_ref = in.readString();
        this.setTo(in.readString());
        this.setFrom(in.readString());
        base = (1==in.readInt());
        personal = (1==in.readInt());
        visibile = (1==in.readInt());
    }

	public Measures() {}

	public static final Parcelable.Creator<Measures> CREATOR
	= new Parcelable.Creator<Measures>() {
		public Measures createFromParcel(Parcel in) {
			return new Measures(in);
		}

		public Measures[] newArray(int size) {
			return new Measures[size];
		}
	};
	
	public String toString() {
		return "id: "+id+" \nsymbol: "+symbol+" \nformula to: "+to+" \nformula from: "+from+" \n";
	}
	
	public String toString(Context context) {
		
		String output = "";
		output+=context.getString(R.string.name) + " " + this.getId() +  "\n";
		output+=context.getString(R.string.symbol) +" "+ symbol;
		return output;
	}

	public String getUnit_ref() {
		return unit_ref;
	}

	public void setUnit_ref(String unit_ref) {
		this.unit_ref = unit_ref;
	}
}



