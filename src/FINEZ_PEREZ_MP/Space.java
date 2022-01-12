package FINEZ_PEREZ_MP;

/** Space defines an abstract class to be inherited by colored spaces (Orange, Blue, Green, Magenta, and Retired space)
 *
 * <p> Each space has a description that determines the event/action to be done in the said space. </p>
 *
 * <p> Each deck is shuffled upon creation. </p>
 *
 * <p> The description can be returned for processing. </p>
 */
abstract class Space
{
    private String description;

    /** instantiates a Space object with a description
     *
     * @param description description of the space
     */
    public Space (String description)
    {
        this.description = description;
    }

    /** returns the description of this space
     *
     * @return space description
     */
    public String getDescription ()
    {
        return description;
    }
}