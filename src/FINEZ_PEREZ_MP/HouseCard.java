package FINEZ_PEREZ_MP;

import java.lang.*;

/** HouseCard defines the house objects available for the players
 *
 * <p> Each card has a name, buyPrice, sellPrice, and endPri that determines what the house is and its value. </p>
 *
 * <p> A house card is created with 4 parameters which cannot be changed after its creation. These parameters will determine what the house is, how much is needed to buy it, and how much its worth when sold in the middle or end of the game. </p>
 *
 * <p> The player that owns the card has the house that card indicates. </p>
 *
 * <p> When the player chooses to replace their house, their old HouseCard are sold and returned to the deck. </p>
 */
public class HouseCard
{
    private String name;
    private int buyPrice;
    private int sellPrice;
    private int endPrice;

    /** instantiates a HouseCard object with its name, buyPrice, sellPrice , and endPrice
     *
     * @param name name of the house assigned to HouseCard
     * @param buyPrice buy amount of the house
     * @param sellPrice sell amount of the house
     * @param endPrice price of the house at the end of the game
     */
    public HouseCard (String name, int buyPrice, int sellPrice, int endPrice)
    {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.endPrice = endPrice;
    }

    /** returns the name of the house
     *
     * @return name of HouseCard
     */
    public String getName ()
    {
        return name;
    }

    /** returns how much the house costs
     *
     * @return buyPrice of HouseCard
     */
    public int getBuyPrice ()
    {
        return buyPrice;
    }

    /** returns how much the house is worth when sold in the middle of the game
     *
     * @return sellPrice of HouseCard
     */
    public int getSellPrice ()
    {
        return sellPrice;
    }

    /** returns how much the house is worth when sold in the end of the game
     *
     * @return endPrice of HouseCard
     */
    public int getEndPrice ()
    {
        return endPrice;
    }
}