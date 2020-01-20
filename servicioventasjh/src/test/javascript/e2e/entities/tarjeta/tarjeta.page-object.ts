import { element, by, ElementFinder } from 'protractor';

export class TarjetaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-tarjeta div table .btn-danger'));
  title = element.all(by.css('jhi-tarjeta div h2#page-heading span')).first();

  async clickOnCreateButton(timeout?: number) {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(timeout?: number) {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class TarjetaUpdatePage {
  pageTitle = element(by.id('jhi-tarjeta-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  tarjeta_idInput = element(by.id('field_tarjeta_id'));
  cod_seguridadInput = element(by.id('field_cod_seguridad'));
  vencimientoInput = element(by.id('field_vencimiento'));
  montoInput = element(by.id('field_monto'));
  numeroInput = element(by.id('field_numero'));
  tipoInput = element(by.id('field_tipo'));
  clienteSelect = element(by.id('field_cliente'));

  async getPageTitle() {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setTarjeta_idInput(tarjeta_id) {
    await this.tarjeta_idInput.sendKeys(tarjeta_id);
  }

  async getTarjeta_idInput() {
    return await this.tarjeta_idInput.getAttribute('value');
  }

  async setCod_seguridadInput(cod_seguridad) {
    await this.cod_seguridadInput.sendKeys(cod_seguridad);
  }

  async getCod_seguridadInput() {
    return await this.cod_seguridadInput.getAttribute('value');
  }

  async setVencimientoInput(vencimiento) {
    await this.vencimientoInput.sendKeys(vencimiento);
  }

  async getVencimientoInput() {
    return await this.vencimientoInput.getAttribute('value');
  }

  async setMontoInput(monto) {
    await this.montoInput.sendKeys(monto);
  }

  async getMontoInput() {
    return await this.montoInput.getAttribute('value');
  }

  async setNumeroInput(numero) {
    await this.numeroInput.sendKeys(numero);
  }

  async getNumeroInput() {
    return await this.numeroInput.getAttribute('value');
  }

  async setTipoInput(tipo) {
    await this.tipoInput.sendKeys(tipo);
  }

  async getTipoInput() {
    return await this.tipoInput.getAttribute('value');
  }

  async clienteSelectLastOption(timeout?: number) {
    await this.clienteSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async clienteSelectOption(option) {
    await this.clienteSelect.sendKeys(option);
  }

  getClienteSelect(): ElementFinder {
    return this.clienteSelect;
  }

  async getClienteSelectedOption() {
    return await this.clienteSelect.element(by.css('option:checked')).getText();
  }

  async save(timeout?: number) {
    await this.saveButton.click();
  }

  async cancel(timeout?: number) {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class TarjetaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-tarjeta-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-tarjeta'));

  async getDialogTitle() {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(timeout?: number) {
    await this.confirmButton.click();
  }
}
