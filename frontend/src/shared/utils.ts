export const truncateText = (text: string | undefined, limit = 20): string => {
    if (typeof text === 'string' && text.length > limit) {
        return text.slice(0, limit) + ' ...';
    }
    return text || ''; // Return an empty string if text is undefined
};
