function openModal(t) {
  document.getElementById('overlay').classList.add('show');
  switchTab(t);
}

function closeModal() {
  document.getElementById('overlay').classList.remove('show');
}

function bgClose(e) {
  if(e.target.id === 'overlay') closeModal();
}

function switchTab(t) {
  document.querySelectorAll('.ftab').forEach((b,i) => b.classList.toggle('active', i===0 ? t==='login' : t==='register'));
  document.getElementById('pane-login').classList.toggle('active', t==='login');
  document.getElementById('pane-register').classList.toggle('active', t==='register');
}

document.addEventListener('keydown', e => {
  if(e.key === 'Escape') closeModal();
});