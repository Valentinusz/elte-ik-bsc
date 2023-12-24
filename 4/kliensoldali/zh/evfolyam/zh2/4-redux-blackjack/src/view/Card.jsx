import { getSymbolFromCardId } from "../utils/blackJackUtils";
import cn from "classnames";

export default function Card({ cardId }) {
    return (
        <span className={cn({'card': true, 'red': cardId >= 13 && cardId <= 38})}>{getSymbolFromCardId(cardId)}</span>
    );
}