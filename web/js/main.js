$(document).ready(() => {

   $('.edit-item-button').click(() => {
      const action = $(this).parent().$('.edit-item-id').val();
      $('edit-item-modal-ok').attr('action', action);
   });
});