import { getValueOfCards } from "../utils/blackJackUtils";
import Card from "./Card";

export default function Hand({ cards }) {
    return (
        <>
            <div>
                {cards.map((cardId) => (
                    <Card key={cardId} cardId={cardId}></Card>
                ))}
            </div>
            <p>Pontszám: {getValueOfCards(cards)}</p>
        </>
    );
}