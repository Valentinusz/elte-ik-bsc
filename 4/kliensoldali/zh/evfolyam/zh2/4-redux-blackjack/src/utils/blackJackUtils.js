const cards = [ '🂡',	'🂢',	'🂣',	'🂤',	'🂥',	'🂦',	'🂧',	'🂨',	'🂩',	'🂪',	'🂫',	'🂭',   '🂮',
                '🂱',	'🂲',	'🂳',   '🂴',	'🂵',	'🂶',	'🂷',	'🂸',	'🂹',	'🂺',	'🂻',	'🂽',	'🂾',
                '🃁',	'🃂',	'🃃',	'🃄',	'🃅',	'🃆',	'🃇',	'🃈',	'🃉',	'🃊',	'🃋',	'🃍',	'🃎',
                '🃑',   '🃒',	'🃓',	'🃔',	'🃕',	'🃖',	'🃗',	'🃘',	'🃙',	'🃚',	'🃛',	'🃝',	'🃞'];

export function getSymbolFromCardId(id) {
    return cards[id];
};

export function getValueFromCardId(id) {
    const numb = id % 13 + 1;
    return numb === 1 || numb >= 10 ? 10 : numb;
}

export function getValueOfCards(hand) {
    return hand.reduce((s, a) => s + getValueFromCardId(a), 0);
}