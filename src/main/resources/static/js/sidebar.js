function toggleSection(header) {
    header.classList.toggle('collapsed');
    const links = header.nextElementSibling;
    links.style.display = links.style.display === 'none' ? 'block' : 'none';
}
