public interface RequestGoal extends Serializable {
	public long getGoalId();
	public List<RequestAccount> getAccounts();
	public RequestGoalConfiguration getConfiguration();
}

public interface RequestAccount extends Serializable {
	public long getAccountNumber();
	public List<RequestHolding> getHoldings();
	public TaxStatusType getTaxStatusType();
}

public interface RequestHolding extends Serializable {
	public String getAssetId();
	public String getHoldingId();
	public Double getValue();
	public Double getCapitalGain();
	public AssetType getAssetType();
	public RepositionAction getRepositionAction();
	public long getOrginalAccountNbr();
}

public enum TaxStatusType {
	TAX_DEFERRED,
	TAX_FREE,
	TAXABLE
}

public enum AssetType {
	
	VANGUARD_FUND_ID("PORT"),
	CUSIP("CUSP"),
	TICKER("TICK"),
	SEDOL("SEDL"),
	ISIN("ISIN");
	
	private String type;

	private AssetType(String type) {
		this.type = type;
	}
	
	public static AssetType toEnum(String type) {
		for (AssetType assetType : AssetType.values()) {
			if (assetType.getType().equals(type)) {
				return assetType;
			}
		}
		return null;
	}
	
	public String getType() {
		return this.type;
	}

}

public enum RepositionAction {
	EVALUATE("RCMD"),
	FORCED_HOLD("DHLD"),
	YES("SELL"),
	NO("HOLD");

	private String repositionActionCode;
	private static final Map<String, RepositionAction> STRING_TO_ENUM = new HashMap<String, RepositionAction>();

	static {
		final RepositionAction[] repositionActionValues = values();
		for (RepositionAction repositionActionValue : repositionActionValues) {
			STRING_TO_ENUM.put(repositionActionValue.getRepositionActionCode(), repositionActionValue);
		}
	}

	private RepositionAction(String repositionCode) {
		this.repositionActionCode = repositionCode;
	}

	public String getRepositionActionCode() {
		return repositionActionCode;
	}

	public static RepositionAction getRepositionActionFromCode(String repositionActionCode) {
		return STRING_TO_ENUM.get(repositionActionCode);
	}
}

public interface RequestGoalConfiguration extends Serializable {
	public List<String> getPreferredFunds();
	public List<String> getCompletionFunds();
	public RequestTargetAssetAllocation getTargetAssetAllocation();
	public BondTilt getBondTilt();
	public EquityTilt getEquityTilt();
	public Map<SubAssetClass, SubAssetClassificationTargetAndRange> getOverrideSubAssetClassificationTargetAndRange();
}

public interface RequestTargetAssetAllocation extends Serializable {
	public int getStock();
	public int getBond();
	public int getCash();
}

public enum BondTilt {
	INDX,
	CORP
}

public enum EquityTilt {
	INDX,
	ACTV;
}


public enum SubAssetClass {
	
	ROOT(SubAssetClassConstants.ROOT, SubAssetClassConstants.ROOT, SubAssetClassConstants.ROOT, "Root"),
	//STOCK
	EQUITY(SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.ROOT, SubAssetClassConstants.REBAL_A1, "Equity"),
	
	//Core Stock
	CORE_EQUITY(SubAssetClassConstants.REBAL_1, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.REBAL_A1, "Core Equity"),
	
	//US Core Stocks
	US_EQUITY(SubAssetClassConstants.REBAL_2, SubAssetClassConstants.REBAL_1, SubAssetClassConstants.REBAL_A1, "US Equity"),
	US_LARGE_CAP(SubAssetClassConstants.REBAL_3, SubAssetClassConstants.REBAL_2, SubAssetClassConstants.REBAL_A1, "US Large Cap"),
 	US_LARGE_CAP_VALUE(SubAssetClassConstants.REBAL_4, SubAssetClassConstants.REBAL_3, SubAssetClassConstants.REBAL_A1, "US Large Cap Value"),
 	US_LARGE_CAP_GROWTH(SubAssetClassConstants.REBAL_5, SubAssetClassConstants.REBAL_3, SubAssetClassConstants.REBAL_A1, "US Large Cap Growth"),
 	US_MID_SMALL_CAP(SubAssetClassConstants.REBAL_6, SubAssetClassConstants.REBAL_2, SubAssetClassConstants.REBAL_A1, "US Mid/Small Cap"),
 	
 	//International Core Stocks
 	INTERNATIONAL_EQUITY(SubAssetClassConstants.REBAL_7, SubAssetClassConstants.REBAL_1, SubAssetClassConstants.REBAL_A1, "International Equity"),
 	INTERNATIONAL_DEVELOPED_MARKETS(SubAssetClassConstants.REBAL_8, SubAssetClassConstants.REBAL_7, SubAssetClassConstants.REBAL_A1, "International Developed Markets"),
 	INTERNATIONAL_DEVELOPED_LARGE_MID_CAP(SubAssetClassConstants.REBAL_9, SubAssetClassConstants.REBAL_8, SubAssetClassConstants.REBAL_A1, "International Developed Large/Mid Cap"),
 	INTERNATIONAL_DEVELOPED_SMALL_CAP(SubAssetClassConstants.REBAL_10, SubAssetClassConstants.REBAL_8, SubAssetClassConstants.REBAL_A1, "International Developed Small Cap"),
 	INTERNATIONAL_EMERGING_MARKETS(SubAssetClassConstants.REBAL_11, SubAssetClassConstants.REBAL_7, SubAssetClassConstants.REBAL_A1, "International Emerging Markets"),
 	
 	//Non Core Stock
 	NON_CORE_EQUITY(SubAssetClassConstants.REBAL_12, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.REBAL_A1, "Non Core Equity"),
 	
 	//Individual Stocks
 	INDIVIDUAL_STOCKS(SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_12, SubAssetClassConstants.REBAL_A1, "Individual Stocks"),
 	OIL_AND_GAS(SubAssetClassConstants.REBAL_14, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_OIL_AND_GAS),
 	BASIC_MATERIALS(SubAssetClassConstants.REBAL_15, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_BASIC_MATERIALS),
 	INDUSTRIALS(SubAssetClassConstants.REBAL_16, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_INDUSTRIALS),
 	CONSUMER_GOODS(SubAssetClassConstants.REBAL_17, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_CONSUMER_GOODS),
 	HEALTH_CARE(SubAssetClassConstants.REBAL_18, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_HEALTH_CARE),
 	CONSUMER_SERVICES(SubAssetClassConstants.REBAL_19, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_CONSUMER_SERVICES),
 	TELECOMMUNICATIONS(SubAssetClassConstants.REBAL_20, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_TELECOMMUNICATIONS),
 	UTILITIES(SubAssetClassConstants.REBAL_21, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_UTILITIES),
 	FINANCIALS(SubAssetClassConstants.REBAL_22, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_FINANCIALS),
 	TECHNOLOGY(SubAssetClassConstants.REBAL_23, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_TECHNOLOGY),
  	INDUSTRIAL_OTHER(SubAssetClassConstants.REBAL_86, SubAssetClassConstants.REBAL_13, SubAssetClassConstants.REBAL_A1, "Industrial - Other"),
 	
 	//Stock Sector Funds
 	EQUITY_SECTOR_FUNDS(SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_12, SubAssetClassConstants.REBAL_A1, "Equity Sector Funds"),
 	OIL_AND_GAS_SECTOR(SubAssetClassConstants.REBAL_25, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_OIL_AND_GAS),
 	BASIC_MATERIALS_SECTOR(SubAssetClassConstants.REBAL_26, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_BASIC_MATERIALS),
 	INDUSTRIALS_SECTOR(SubAssetClassConstants.REBAL_27, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_INDUSTRIALS),
 	CONSUMER_GOODS_SECTOR(SubAssetClassConstants.REBAL_28, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_CONSUMER_GOODS),
 	HEALTH_CARE_SECTOR(SubAssetClassConstants.REBAL_29, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_HEALTH_CARE),
 	CONSUMER_SERVICES_SECTOR(SubAssetClassConstants.REBAL_30, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_CONSUMER_SERVICES),
 	TELECOMMUNICATIONS_SECTOR(SubAssetClassConstants.REBAL_31, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_TELECOMMUNICATIONS),
 	UTILITIES_SECTOR(SubAssetClassConstants.REBAL_32, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_UTILITIES),
 	FINANCIALS_SECTOR(SubAssetClassConstants.REBAL_33, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_FINANCIALS),
 	TECHNOLOGY_SECTOR(SubAssetClassConstants.REBAL_34, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, SubAssetClassConstants.NAME_TECHNOLOGY),
 	REGION(SubAssetClassConstants.REBAL_35, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, "Region"),
 	COUNTRY(SubAssetClassConstants.REBAL_36, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, "Country"),
 	MULTI_SECTOR(SubAssetClassConstants.REBAL_37, SubAssetClassConstants.REBAL_24, SubAssetClassConstants.REBAL_A1, "Multi Sector"),
 	
 	//High Risk Alternatives
 	HIGH_RISK_ALTERNATIVES(SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_12, SubAssetClassConstants.REBAL_A1, "High Risk Alternatives"),
 	COMMODITY_FUTURES(SubAssetClassConstants.REBAL_39, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Commodity Futures"),
 	PHYSICAL_COMMODITY_EQUIVALENTS(SubAssetClassConstants.REBAL_40, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Physical Commodity Equivalents"),
 	LEVERAGED_EQUITY_BONDS(SubAssetClassConstants.REBAL_41, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Leveraged Equity/Bonds"),
 	CURRENCY(SubAssetClassConstants.REBAL_42, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Currency"),
 	MANAGED_FUTURES(SubAssetClassConstants.REBAL_43, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Managed Futures"),
 	ABSOLUTE_RETURN(SubAssetClassConstants.REBAL_44, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Absolute Return"),
 	LONG_SHORT_EQUITY_OR_VOLATILITY(SubAssetClassConstants.REBAL_45, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Long/Short Equity or Volatility"),
 	SHORT_BIAS(SubAssetClassConstants.REBAL_46, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1, "Short Bias"),
 	SPECULATIVE_AND_HEDGING_DERIVATIVES(SubAssetClassConstants.REBAL_47, SubAssetClassConstants.REBAL_38, SubAssetClassConstants.REBAL_A1,  "Speculative & Hedging Derivatives"),
 	
 	//BOND
 	BONDS(SubAssetClassConstants.REBAL_A2, SubAssetClassConstants.ROOT, SubAssetClassConstants.REBAL_A2, "Bonds"),
 	
 	//Core Bond
 	CORE_BONDS(SubAssetClassConstants.REBAL_48, SubAssetClassConstants.REBAL_A2, SubAssetClassConstants.REBAL_A2, "Core Bonds"),
 	
 	//Core Taxable Bond
 	BOND_TAXABLE(SubAssetClassConstants.REBAL_49, SubAssetClassConstants.REBAL_48, SubAssetClassConstants.REBAL_A2, "Bond Taxable"),
 	
 	//US Taxable Bonds
 	TAXABLE_US_BY_CURRENCY(SubAssetClassConstants.REBAL_50, SubAssetClassConstants.REBAL_49, SubAssetClassConstants.REBAL_A2, "Taxable US by Currency"),
  	TAXABLE_US_SHORT_TERM_BY_CURRENCY(SubAssetClassConstants.REBAL_51, SubAssetClassConstants.REBAL_50, SubAssetClassConstants.REBAL_A2, "Taxable US Short Term by Currency"),
  	TAXABLE_US_INTERMEDIATE_TERM_BY_CURRENCY(SubAssetClassConstants.REBAL_52, SubAssetClassConstants.REBAL_50, SubAssetClassConstants.REBAL_A2, "Taxable US Intermediate Term by Currency"),
  	TAXABLE_US_LONG_TERM_BY_CURRENCY(SubAssetClassConstants.REBAL_53, SubAssetClassConstants.REBAL_50, SubAssetClassConstants.REBAL_A2, "Taxable US Long Term by Currency"),
  	
  	//International Taxable Bonds
  	TAXABLE_INTERNATIONAL_BY_CURRENCY(SubAssetClassConstants.REBAL_54, SubAssetClassConstants.REBAL_49, SubAssetClassConstants.REBAL_A2, "Taxable International by Currency"),
  	TAXABLE_INTERNATIONAL_DEVELOPED_USD_HEDG(SubAssetClassConstants.REBAL_55, SubAssetClassConstants.REBAL_54, SubAssetClassConstants.REBAL_A2, "Taxable International Developed USD Hedg"),
  	
  	//Tax Exempt Bonds
  	BOND_TAX_EXEMPT(SubAssetClassConstants.REBAL_56, SubAssetClassConstants.REBAL_48, SubAssetClassConstants.REBAL_A2, "Bond Tax Exempt"),
  	TAX_EXEMPT_SHORT_TERM(SubAssetClassConstants.REBAL_57, SubAssetClassConstants.REBAL_56, SubAssetClassConstants.REBAL_A2, "Tax Exempt Short Term"),
  	TAX_EXEMPT_INTERMEDIATE_TERM(SubAssetClassConstants.REBAL_58, SubAssetClassConstants.REBAL_56, SubAssetClassConstants.REBAL_A2, "Tax Exempt Intermediate Term"),
  	TAX_EXEMPT_LONG_TERM(SubAssetClassConstants.REBAL_59, SubAssetClassConstants.REBAL_56, SubAssetClassConstants.REBAL_A2, "Tax Exempt Long Term"),
  	
  	//Non Core Bond
  	NON_CORE_BONDS(SubAssetClassConstants.REBAL_60, SubAssetClassConstants.REBAL_A2, SubAssetClassConstants.REBAL_A2, "Non Core Bonds"),
  	
  	//Bond Sectors
  	BOND_SECTOR(SubAssetClassConstants.REBAL_61, SubAssetClassConstants.REBAL_60, SubAssetClassConstants.REBAL_A2, "Bond Sector"),
  	MORTGAGE_BACKED_SECURITIES(SubAssetClassConstants.REBAL_62, SubAssetClassConstants.REBAL_61, SubAssetClassConstants.REBAL_A2, "Mortgage Backed Securities"),
  	INFLATION_LINKED(SubAssetClassConstants.REBAL_63, SubAssetClassConstants.REBAL_61, SubAssetClassConstants.REBAL_A2, "Inflation Linked"),
  	
  	//Hybrid Bond Sectors
  	BOND_HYBRID_SECTOR(SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_60, SubAssetClassConstants.REBAL_A2, "Bond Hybrid Sector"),
  	TAXABLE_HIGH_YIELD(SubAssetClassConstants.REBAL_65, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Taxable High Yield"),
  	TAX_EXEMPT_HIGH_YIELD(SubAssetClassConstants.REBAL_66, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Tax Exempt High Yield"),
  	CONVERTIBLE_BONDS(SubAssetClassConstants.REBAL_67, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Convertible Bonds"),
  	EMERGING_MARKET_BONDS(SubAssetClassConstants.REBAL_68, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Emerging Market Bonds"),
  	PREFERRED(SubAssetClassConstants.REBAL_69, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Preferred"),
  	
  	//Low Risk Alternatives
  	LOW_RISK_ALTERNATIVES(SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_60, SubAssetClassConstants.REBAL_A2, "Low Risk Alternatives"),
  	CHARITABLE_ENDOWMENT_POOL(SubAssetClassConstants.REBAL_71, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Charitable Endowment Pool"),
  	ANNUITY_INSURANCE_POLLICY(SubAssetClassConstants.REBAL_72, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Annuity Insurance Pollicy"),
  	ESCROW_RECEIPTS(SubAssetClassConstants.REBAL_73, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Escrow Receipts"),
//  	VARIABLE_ANNUITY_INSURANCE_PLAN_SUB_ACCT(SubAssetClassConstants.REBAL_74, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Variable Annuity Insurance Plan Sub Acct"),
  	LETTER_OF_GUARANTEE(SubAssetClassConstants.REBAL_75, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Letter of Guarantee"),
  	BANK_PLEDGE(SubAssetClassConstants.REBAL_76, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Bank Pledge"),
  	VARIABLE_ANNUITY_PLAN(SubAssetClassConstants.REBAL_77, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Variable Annuity Plan"),
  	FIXED_ANNUITY_PLAN(SubAssetClassConstants.REBAL_78, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Fixed Annuity Plan"),
  	LOAN_PARTICIPATION(SubAssetClassConstants.REBAL_79, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Loan Participation"),
  	UNCONSTRAINED_FIXED_INCOME(SubAssetClassConstants.REBAL_80, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Unconstrained Fixed Income"),
  	FIXED_INCOME_RELATIVE_VALUE(SubAssetClassConstants.REBAL_81, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Fixed Income Relative Value"),
  	MARKET_NEUTRAL(SubAssetClassConstants.REBAL_82, SubAssetClassConstants.REBAL_70, SubAssetClassConstants.REBAL_A2, "Market Neutral"),
  	
  	//CASH
  	CASH(SubAssetClassConstants.REBAL_A3, SubAssetClassConstants.ROOT, SubAssetClassConstants.REBAL_A3, "Cash"),
  	
  	//Cash Taxable
  	CASH_TAXABLE(SubAssetClassConstants.REBAL_83, SubAssetClassConstants.REBAL_A3, SubAssetClassConstants.REBAL_A3, "Cash Taxable"),
  	
  	//Cash Tax Exempt
  	CASH_TAX_EXEMPT(SubAssetClassConstants.REBAL_84, SubAssetClassConstants.REBAL_A3, SubAssetClassConstants.REBAL_A3, "Cash Tax Exempt"),
  	
  	//Hybrid Bond Sectors (cont'd)
  	NON_USD_UNHEDGED(SubAssetClassConstants.REBAL_85, SubAssetClassConstants.REBAL_64, SubAssetClassConstants.REBAL_A2, "Non USD Unhedged"),
  	
  	UNCLASSIFIED(SubAssetClassConstants.UNCLASSIFIED, SubAssetClassConstants.ROOT, SubAssetClassConstants.ROOT, "Unclassified");

	private String id;
	private String parentId;
	private String rootId;
	private String name;
	
	

	/*
	 * constructor
	 */
	SubAssetClass(String individualClassId, String parentClassId, String rootId, String assetClassName) {
		this.id = individualClassId;
		this.parentId = parentClassId;
		this.rootId = rootId;
		this.name = assetClassName;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getParentId() {
		return this.parentId;
	}
	
	public String getRootId() {
		return this.rootId;
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public static SubAssetClass toEnum(String id) {
		return getSubAssetClassById(id);
	}
	
	/*
	 * Return SubAssetClass for matching subAssetClassId.
	 * TODO: Till early June 2014, also check for matching name when Id is not found.
	 */
	public static SubAssetClass getSubAssetClassById(String subAssetClassId) {
		SubAssetClass subAssetClass = null;
		
		for (SubAssetClass node : SubAssetClass.values()) {
			if (node.getId().equals(subAssetClassId)) {
				subAssetClass = node;
				break;
			}
		}

		return subAssetClass;
	}
	
	/*
	 * Return SubAssetClass for matching subAssetClassName
	 */
	public static SubAssetClass getSubAssetClassByName(String assetName) {
		SubAssetClass subAssetClass = null;
		
		for (SubAssetClass node : SubAssetClass.values()) {
			if (node.getName().equals(assetName)) {
				subAssetClass = node;
				break;
			}
		}
		return subAssetClass;
	}
	
	/*
	 * Return all immediate children SubAssetClasses for a parent ID 
	 */
	public static List<SubAssetClass> getSubAssetClassesByParentId(String parentCode) {
		List<SubAssetClass> nodes = new ArrayList<SubAssetClass>(16);
		for (SubAssetClass node : SubAssetClass.values()) {
			if (node.getParentId().equals(parentCode)) {
				nodes.add(node);
			}
		}
		return nodes;
	}	
}

public interface SubAssetClassificationTargetAndRange extends Serializable {
	public Double getTarget();
	public Double getUpperLimit();
	public Double getLowerLimit();
	public boolean hasUpperLimit();
	public boolean hasLowerLimit();
}

public interface RebalanceRequest extends Serializable {
	public List<RequestGoal> getGoals();
	public RequestConfiguration getRebalanceConfiguration();
	public long getClientPoid();
}

public interface RequestConfiguration extends Serializable {
	public boolean hasAdmiralFundMinimumOverride();
	public double getCapitalGainThresholdCore();
	public double getCapitalGainThresholdNonCore();
	public double getTierOneBreakEvenYearThresholdCore();
	public double getLongTermCapitalGainTaxRate();
	public double getTierOneBreakEvenYearThresholdNonCore();
	public double getVanguardPreferredFundAverageExpenseRatio();
	public TaxBracket getTaxBracket();
	public Map<SubAssetClass, SubAssetClassificationTargetAndRange> getSubAssetClassTargets();
	public List<RequestPurchaseOrder> getPurchaseOrders();
	public double getIndexBondsInCorporateOverweightTiltPercentage();
	public double getIndividualStocksConcentrationLimit();
	public List<RequestAggregateSectorConcentrationLimit> getAggregateSectorConcetrationLimits();
}

public enum TaxBracket {
	HIGH,
	LOW
}

public interface RequestPurchaseOrder extends Serializable {
	List<TaxStatusType> getAccountTaxStatusOrder();
	List<RequestPurchaseOrderEntry> getRequestPurchaseOrderEntries();
	PurchaseOrderType getPurchaseOrderType();
}

public enum TaxStatusType {
	TAX_DEFERRED,
	TAX_FREE,
	TAXABLE
}

public interface RequestPurchaseOrderEntry extends Serializable {
    public SubAssetClass getSubAssetClassId();
    public InvestmentStrategy getInvestmentStrategy();
}

public enum InvestmentStrategy {
	NON_EQUITY,
	EQUITY_INDEX,
	EQUITY_ACTIVE;
}

public enum PurchaseOrderType {
	PATH_A_MULTIPLE_RETIREMENT,
	PATH_B,
	PATH_C_TAXABLE_CASH_BOND,
	PATH_C_EQUITY_TAXEXEMPT_CASH_BOND;
}

public interface RequestAggregateSectorConcentrationLimit extends Serializable {
	double getConcentrationLimit();
	List<SubAssetClass> getRelatedSubAssetClasses();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface VgiMockVariable {

}

